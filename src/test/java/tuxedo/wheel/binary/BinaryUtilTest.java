package tuxedo.wheel.binary;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryUtilTest {
    @Test
    public void test() {
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 0), "00000000");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 1), "00000001");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) -1), "11111111");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 85), "01010101");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) -86), "10101010");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 127), "01111111");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) -128), "10000000");
    }
}
