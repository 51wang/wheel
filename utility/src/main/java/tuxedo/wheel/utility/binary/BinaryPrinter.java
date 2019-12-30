package tuxedo.wheel.utility.binary;

import java.io.IOException;
import java.io.OutputStream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryPrinter {
    private final @NonNull OutputStream outputStream;
    private ByteEncoder byteEncoder = ByteEncoder.TO_HEX;
    private byte[] lineSeparator = "\n".getBytes();
    private byte[] blockSeparator = "\t".getBytes();
    private byte[] byteSeparator = " ".getBytes();
    private int blockSize = 8;

    public BinaryPrinter setByteEncoder(@NonNull ByteEncoder byteEncoder) {
        this.byteEncoder = byteEncoder;
        return this;
    }

    public BinaryPrinter setLineSeparator(@NonNull String lineSeparator) {
        this.lineSeparator = lineSeparator.getBytes();
        return this;
    }

    public BinaryPrinter setBlockSeparator(@NonNull String blockSeparator) {
        this.blockSeparator = blockSeparator.getBytes();
        return this;
    }

    public BinaryPrinter setByteSeparator(@NonNull String byteSeparator) {
        this.byteSeparator = byteSeparator.getBytes();
        return this;
    }

    public BinaryPrinter setBlockSize(int blockSize) {
        if (blockSize <= 0) {
            throw new IllegalArgumentException("BlockSize should be positive!");
        }
        this.blockSize = blockSize;
        return this;
    }

    public void print(byte[] bytes) throws IOException {
        for (int i = 1; i <= bytes.length; i++) {
            outputStream.write(byteEncoder.encode(bytes[i - 1]).getBytes());
            if (i == bytes.length) {
                outputStream.write(lineSeparator);
            } else if (i % blockSize == 0) {
                if (i % (blockSize * 2) == 0) {
                    outputStream.write(lineSeparator);
                } else {
                    outputStream.write(blockSeparator);
                }
            } else {
                outputStream.write(byteSeparator);
            }
        }
    }
}
