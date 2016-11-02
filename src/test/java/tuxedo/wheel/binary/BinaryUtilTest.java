package tuxedo.wheel.binary;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryUtilTest {
    @Test
    public void testToStdBinaryString() {
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 0), "00000000");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 1), "00000001");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) -1), "11111111");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 85), "01010101");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) -86), "10101010");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) 127), "01111111");
        Assert.assertEquals(BinaryUtil.toStdBinaryString((byte) -128), "10000000");
    }

    @Test
    public void testToStdHexString() {
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) 0), "00");
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) 1), "01");
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) -1), "ff");
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) 85), "55");
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) -86), "aa");
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) 127), "7f");
        Assert.assertEquals(BinaryUtil.toStdHexString((byte) -128), "80");
    }
}
