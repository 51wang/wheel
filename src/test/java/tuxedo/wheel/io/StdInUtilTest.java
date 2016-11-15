package tuxedo.wheel.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class StdInUtilTest {
    private final InputStream ref = System.in;

    @Test
    public void testNextLine() throws IOException {
        System.setIn(new ByteArrayInputStream("hello\n".getBytes()));
        Assert.assertEquals(StdInUtil.nextLine(), "hello");
    }

    @Test
    public void testNextFloat() throws IOException {
        System.setIn(new ByteArrayInputStream("1.1\n".getBytes()));
        Assert.assertEquals(StdInUtil.nextFloat(), 1.1f, Float.MIN_NORMAL);
    }

    @Test
    public void testNextDouble() throws IOException {
        System.setIn(new ByteArrayInputStream("22.2\n".getBytes()));
        Assert.assertEquals(StdInUtil.nextDouble(), 22.2d, Double.MIN_NORMAL);
    }

    @Test
    public void testNextInt() throws IOException {
        System.setIn(new ByteArrayInputStream("3333\n".getBytes()));
        Assert.assertEquals(StdInUtil.nextInt(), 3333);
    }

    @Test
    public void testNextLong() throws IOException {
        System.setIn(new ByteArrayInputStream("44444\n".getBytes()));
        Assert.assertEquals(StdInUtil.nextLong(), 44444L);
    }

    @Test
    public void testNextBoolean() throws IOException {
        System.setIn(new ByteArrayInputStream("true\n".getBytes()));
        Assert.assertTrue(StdInUtil.nextBoolean());
        System.setIn(new ByteArrayInputStream("false\n".getBytes()));
        Assert.assertFalse(StdInUtil.nextBoolean());
    }

    @AfterTest
    public void after() {
        System.setIn(ref);
    }
}
