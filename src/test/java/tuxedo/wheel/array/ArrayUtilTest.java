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

    @Test
    public void testReverse() {
        __testReverse(new String[] { "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d" });
        __testReverse(new String[] { "e", "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d", "e" });
    }

    private void __testReverse(String[] original, String[] expected) {
        ArrayUtil.reverse(original);
        Assert.assertEquals(original, expected);
    }

    @Test
    public void testCopyAndReverse() {
        __testCopyAndReverse(new String[] { "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d" });
        __testCopyAndReverse(new String[] { "e", "d", "c", "b", "a" }, new String[] { "a", "b", "c", "d", "e" });
    }

    private void __testCopyAndReverse(String[] original, String[] expected) {
        String[] reversed = ArrayUtil.copyAndReverse(original);
        Assert.assertNotSame(reversed, original);
        Assert.assertEquals(reversed, expected);
    }
}
