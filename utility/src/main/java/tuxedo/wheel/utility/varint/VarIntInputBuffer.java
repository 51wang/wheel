package tuxedo.wheel.utility.varint;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class VarIntInputBuffer {
    private final @NonNull
    InputStream is;
    private final byte[] buf = new byte[1];

    public byte[] read(int len) throws IOException {
        byte[] b = new byte[len];
        is.read(b);
        return b;
    }

    public void read(byte[] b) throws IOException {
        is.read(b);
    }

    public void read(byte[] b, int off, int len) throws IOException {
        is.read(b, off, len);
    }

    public byte readByte() throws IOException {
        read(buf);
        return buf[0];
    }

    public short readShort() throws IOException {
        return (short) (((readByte() & 0xff) << 8) | ((readByte() & 0xff)));
    }

    public int readVarInt() throws IOException {
        int v = 0;
        int len = 0;
        for (; ; ) {
            byte b = readByte();
            if ((b & ~0x7F) == 0) {
                v += (b & 0xFF) << (len * 7);
                break;
            } else {
                v += (b & 0x7F) << (len++ * 7);
            }
        }
        return v;
    }

    public long readVarLong() throws IOException {
        long v = 0;
        int len = 0;
        for (; ; ) {
            byte b = readByte();
            if ((b & ~0x7FL) == 0 || len == 8) {
                v += (b & 0xFFL) << (len * 7);
                break;
            } else {
                v += (b & 0x7FL) << (len++ * 7);
            }
        }
        return v;
    }

    public byte[] readVarBinary() throws IOException {
        return read(readVarInt());
    }

    public String readVarString() throws IOException {
        return new String(readVarBinary());
    }
}
