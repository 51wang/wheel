package tuxedo.wheel.stack;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StackDepthCounterTest {
    private final StackDepthCounter counter = new StackDepthCounter();

    @Test(threadPoolSize = 20, invocationCount = 200)
    public void test() {
        counter.enter();
        Assert.assertTrue(counter.quit());
        counter.enter();
        counter.reset();
        counter.enter();
        Assert.assertTrue(counter.quit());
        counter.enter();
        counter.enter();
        counter.enter();
        Assert.assertFalse(counter.quit());
        Assert.assertFalse(counter.quit());
        Assert.assertTrue(counter.quit());
    }
}
