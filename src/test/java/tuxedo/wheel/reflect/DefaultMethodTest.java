package tuxedo.wheel.reflect;

import java.lang.invoke.WrongMethodTypeException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultMethodTest {
    @Test(expectedExceptions = { WrongMethodTypeException.class })
    public void testNonDefault() throws ReflectiveOperationException {
        new DefaultMethod(Student.class.getDeclaredMethod("id"));
    }

    @Test
    public void testDefault() throws Throwable {
        Assert.assertEquals(new DefaultMethod(Student.class.getDeclaredMethod("name")).invoke(null), "unknown");
    }

    private static interface Student {
        long id();

        @SuppressWarnings("unused")
        default String name() {
            return "unknown";
        }
    }
}
