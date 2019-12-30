package tuxedo.wheel.utility.binary;

@FunctionalInterface
public interface ByteEncoder {
    ByteEncoder TO_BINARY = BinaryUtil::toStdBinaryString;
    ByteEncoder TO_HEX = BinaryUtil::toStdHexString;

    String encode(byte b);
}
