package tuxedo.wheel.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryPrinterTest {
    @Test
    public void test() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        new BinaryPrinter(os).setByteSeparator("#").setLineSeparator("@").setBytesPerLine(3).print(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        Assert.assertEquals(os.toString(), "00000001#00000010#00000011@00000100#00000101#00000110@00000111#00001000@");
    }
}
