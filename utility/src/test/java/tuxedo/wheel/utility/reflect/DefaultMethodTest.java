package tuxedo.wheel.utility.reflect;

import java.lang.invoke.WrongMethodTypeException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultMethodTest {
    @Test(threadPoolSize = 10, invocationCount = 100, expectedExceptions = { WrongMethodTypeException.class })
    public void testNonDefault() throws ReflectiveOperationException {
        DefaultMethod.newInstance(Student.class.getDeclaredMethod("id"));
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void testDefault() throws Throwable {
        Assert.assertEquals(DefaultMethod.newInstance(Student.class.getDeclaredMethod("name")).invoke(null), "unknown");
    }

    private static interface Student {
        long id();

        @SuppressWarnings("unused")
        default String name() {
            return "unknown";
        }
    }
}
