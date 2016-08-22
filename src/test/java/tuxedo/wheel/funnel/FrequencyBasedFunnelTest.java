package tuxedo.wheel.funnel;

import java.util.concurrent.atomic.AtomicLong;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class FrequencyBasedFunnelTest {
    private final static int LOOP = 10000;
    private final static int WORKERS = 20;
    private final static long FREQUENCY = 7;
    private final Funnel funnel = new FrequencyBasedFunnel(FREQUENCY);
    private final AtomicLong dripped = new AtomicLong();

    @Test(invocationCount = WORKERS, threadPoolSize = WORKERS)
    public void test() {
        for (int i = 0; i < LOOP; i++) {
            if (funnel.canDrip()) {
                dripped.incrementAndGet();
            }
        }
    }

    @AfterTest
    public void after() {
        Assert.assertEquals(dripped.get(), ((LOOP * WORKERS - 1) / FREQUENCY) + 1);
    }
}
