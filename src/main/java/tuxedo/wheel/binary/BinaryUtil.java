package tuxedo.wheel.binary;

public class BinaryUtil {
    public static String toStdBinaryString(byte b) {
        return Integer.toBinaryString(0x0100 | Byte.toUnsignedInt(b)).substring(1, 9);
    }
}
