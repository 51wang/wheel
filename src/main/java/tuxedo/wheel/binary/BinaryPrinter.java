package tuxedo.wheel.binary;

import java.io.IOException;
import java.io.OutputStream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BinaryPrinter {
    private final @NonNull OutputStream outputStream;
    private String lineSeparator = "\n";
    private String byteSeparator = "\t";
    private int bytesPerLine = 16;

    public BinaryPrinter setLineSeparator(String lineSeparator) {
        if (lineSeparator != null) {
            this.lineSeparator = lineSeparator;
        }
        return this;
    }

    public BinaryPrinter setByteSeparator(String byteSeparator) {
        if (byteSeparator != null) {
            this.byteSeparator = byteSeparator;
        }
        return this;
    }

    public BinaryPrinter setBytesPerLine(int bytesPerLine) {
        if (bytesPerLine > 0) {
            this.bytesPerLine = bytesPerLine;
        }
        return this;
    }

    public void print(byte[] bytes) throws IOException {
        for (int i = 1; i <= bytes.length; i++) {
            outputStream.write(BinaryUtil.toStdBinaryString(bytes[i - 1]).getBytes());
            if (i == bytes.length || i % bytesPerLine == 0) {
                outputStream.write(lineSeparator.getBytes());
            } else {
                outputStream.write(byteSeparator.getBytes());
            }
        }
    }
}
