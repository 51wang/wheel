package tuxedo.wheel.utility.funnel;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public class TimeBasedFunnelTest {
    private final static int LOOP = 100;
    private final static int WORKERS = 20;
    private final static long WAIT = 100;
    private final static long INTERVAL_MILLIS = 10000;
    private final Funnel funnel = new ConcurrentFunnel(new TimeBasedFunnel(INTERVAL_MILLIS));
    private final AtomicLong dripped = new AtomicLong();

    @Test(invocationCount = WORKERS, threadPoolSize = WORKERS)
    public void test() {
        for (int i = 0; i < LOOP; i++) {
            if (funnel.canDrip()) {
                dripped.incrementAndGet();
            }
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(WAIT));
        }
    }

    @AfterTest
    public void after() {
        Assert.assertEquals(dripped.get(), (LOOP * WAIT / INTERVAL_MILLIS) + 1);
    }
}
