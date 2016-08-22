package tuxedo.wheel.concurrent;

import java.util.concurrent.ThreadFactory;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NameableThreadFactoryTest {
    @Test
    public void test() {
        ThreadFactory threadFactory = new NameableThreadFactory("test-workers");
        Assert.assertEquals(threadFactory.newThread(null).getName(), "test-workers-1");
        Assert.assertEquals(threadFactory.newThread(null).getName(), "test-workers-2");
        Assert.assertEquals(threadFactory.newThread(null).getName(), "test-workers-3");
        Assert.assertEquals(threadFactory.newThread(null).getName(), "test-workers-4");
    }
}
