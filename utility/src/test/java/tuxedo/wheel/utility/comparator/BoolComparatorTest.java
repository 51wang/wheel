package tuxedo.wheel.utility.comparator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BoolComparatorTest {
    @Test
    public void testTrueToTrue() {
        __BooleanComparator c = new __BooleanComparator();
        c.compare(true, true);
        Assert.assertFalse(c.changedToFalse);
        Assert.assertFalse(c.changedToTrue);
    }

    @Test
    public void testFalseToFalse() {
        __BooleanComparator c = new __BooleanComparator();
        c.compare(false, false);
        Assert.assertFalse(c.changedToFalse);
        Assert.assertFalse(c.changedToTrue);
    }

    @Test
    public void testTrueToFalse() {
        __BooleanComparator c = new __BooleanComparator();
        c.compare(true, false);
        Assert.assertTrue(c.changedToFalse);
        Assert.assertFalse(c.changedToTrue);
    }

    @Test
    public void testFalseToTrue() {
        __BooleanComparator c = new __BooleanComparator();
        c.compare(false, true);
        Assert.assertFalse(c.changedToFalse);
        Assert.assertTrue(c.changedToTrue);
    }

    private static class __BooleanComparator implements BoolComparator {
        boolean changedToFalse = false;
        boolean changedToTrue = false;

        @Override
        public void changedToFalse() {
            changedToFalse = true;
        }

        @Override
        public void changedToTrue() {
            changedToTrue = true;
        }
    }
}
