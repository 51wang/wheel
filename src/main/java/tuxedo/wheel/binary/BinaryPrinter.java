package tuxedo.wheel.binary;

import java.io.IOException;
import java.io.OutputStream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryPrinter {
    public final static ByteEncoder TOBINARY = BinaryUtil::toStdBinaryString;
    public final static ByteEncoder TOHEX = BinaryUtil::toStdHexString;
    private final @NonNull OutputStream outputStream;
    private ByteEncoder byteEncoder = TOHEX;
    private String lineSeparator = "\n";
    private String blockSeparator = "\t";
    private String byteSeparator = " ";
    private int blockSize = 8;

    public BinaryPrinter setByteEncoder(ByteEncoder byteEncoder) {
        if (byteEncoder != null) {
            this.byteEncoder = byteEncoder;
        }
        return this;
    }

    public BinaryPrinter setLineSeparator(String lineSeparator) {
        if (lineSeparator != null) {
            this.lineSeparator = lineSeparator;
        }
        return this;
    }

    public BinaryPrinter setBlockSeparator(String blockSeparator) {
        if (blockSeparator != null) {
            this.blockSeparator = blockSeparator;
        }
        return this;
    }

    public BinaryPrinter setByteSeparator(String byteSeparator) {
        if (byteSeparator != null) {
            this.byteSeparator = byteSeparator;
        }
        return this;
    }

    public BinaryPrinter setBlockSize(int blockSize) {
        if (blockSize > 0) {
            this.blockSize = blockSize;
        }
        return this;
    }

    public void print(byte[] bytes) throws IOException {
        for (int i = 1; i <= bytes.length; i++) {
            outputStream.write(byteEncoder.encode(bytes[i - 1]).getBytes());
            if (i == bytes.length) {
                outputStream.write(lineSeparator.getBytes());
            } else if (i % blockSize == 0) {
                if (i % (blockSize * 2) == 0) {
                    outputStream.write(lineSeparator.getBytes());
                } else {
                    outputStream.write(blockSeparator.getBytes());
                }
            } else {
                outputStream.write(byteSeparator.getBytes());
            }
        }
    }

    @FunctionalInterface
    public static interface ByteEncoder {
        String encode(byte b);
    }
}
