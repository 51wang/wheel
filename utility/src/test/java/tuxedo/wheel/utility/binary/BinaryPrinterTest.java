package tuxedo.wheel.utility.binary;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BinaryPrinterTest {
    @Test
    public void test() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new BinaryPrinter(os).print(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18});
        Assert.assertEquals(os.toString(), "00 01 02 03 04 05 06 07\t08 09 0a 0b 0c 0d 0e 0f\n10 11 12\n");
    }
}
