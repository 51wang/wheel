package tuxedo.wheel.utility.concurrent;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import tuxedo.wheel.utility.array.ArrayUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class HashingExecutorTest {
    private final static int LOOP = 1000;
    private final static int WORKERS = 10;
    private final Executor executor = new HashingExecutor(WORKERS);
    private final AtomicInteger counter = new AtomicInteger();
    private final AtomicBoolean fail = new AtomicBoolean();
    private final AtomicInteger[] records =
            ArrayUtil.arrayFilledBySupplier(AtomicInteger.class, WORKERS, () -> new AtomicInteger());

    @Test(invocationCount = WORKERS, threadPoolSize = WORKERS)
    public void test() {
        final int index = counter.getAndIncrement();
        IntStream.range(0, LOOP).forEach(i -> executor.execute(new Runnable() {
            @Override
            public void run() {
                if (!records[index].compareAndSet(i, i + 1)) {
                    fail.set(true);
                }
            }

            @Override
            public int hashCode() {
                return index;
            }
        }));
    }

    @AfterTest
    public void after() {
        Assert.assertFalse(fail.get());
    }
}
