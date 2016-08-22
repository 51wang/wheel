package tuxedo.wheel.array;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayUtilTest {
    @Test
    public void testArrayFilledBySupplier() {
        AtomicInteger index = new AtomicInteger();
        String[] expected = new String[] { "a", "b", "c", "d", "e", "f" };
        String[] actual = ArrayUtil.arrayFilledBySupplier(String.class, expected.length, () -> expected[index.getAndIncrement()]);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testArrayFilledByValue() {
        String[] expected = new String[] { "a", "a", "a", "a" };
        String[] actual = ArrayUtil.arrayFilledByValue(String.class, expected.length, "a");
        Assert.assertEquals(actual, expected);
    }
}
