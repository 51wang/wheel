package tuxedo.wheel.utility.varint;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;

@RequiredArgsConstructor
public class VarIntOutputBuffer {
    @NonNull
    private final OutputStream os;
    private final byte[] buf = new byte[1];

    public void write(byte[] b) throws IOException {
        os.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        os.write(b, off, len);
    }

    public void writeByte(byte v) throws IOException {
        buf[0] = v;
        write(buf);
    }

    public void writeShort(short v) throws IOException {
        writeByte((byte) (0xff & (v >> 8)));
        writeByte((byte) (0xff & (v)));
    }

    public void writeVarInt(int v) throws IOException {
        for (; ; ) {
            if ((v & ~0x7F) == 0) {
                writeByte((byte) v);
                break;
            } else {
                writeByte((byte) ((v & 0x7F) | 0x80));
                v >>>= 7;
            }
        }
    }

    public void writeVarLong(long v) throws IOException {
        int len = 0;
        for (; ; ) {
            if ((v & ~0x7FL) == 0 || len == 8) {
                writeByte((byte) v);
                break;
            } else {
                writeByte((byte) ((v & 0x7FL) | 0x80L));
                v >>>= 7;
                len++;
            }
        }
    }

    public void writeVarBinary(@NonNull byte[] v) throws IOException {
        writeVarInt(v.length);
        write(v);
    }

    public void writeVarString(@NonNull String v) throws IOException {
        writeVarBinary(v.getBytes());
    }
}
