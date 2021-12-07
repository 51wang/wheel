package tuxedo.wheel.utility.varint;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BufferTest {
    @Test
    public void testByte() throws IOException {
        final Encoder<Byte> encoder = (ob, v) -> ob.writeByte(v);
        final Decoder<Byte> decoder = (ib) -> ib.readByte();
        __assert((byte) 0, new byte[]{0}, encoder, decoder);
        __assert(Byte.MIN_VALUE, new byte[]{Byte.MIN_VALUE}, encoder, decoder);
        __assert(Byte.MAX_VALUE, new byte[]{Byte.MAX_VALUE}, encoder, decoder);
    }

    @Test
    public void testShort() throws IOException {
        final Encoder<Short> encoder = (ob, v) -> ob.writeShort(v);
        final Decoder<Short> decoder = (ib) -> ib.readShort();
        __assert((short) 0, new byte[]{0, 0}, encoder, decoder);
        __assert(Short.MIN_VALUE, new byte[]{-128, 0}, encoder, decoder);
        __assert(Short.MAX_VALUE, new byte[]{127, -1}, encoder, decoder);
    }

    @Test
    public void testVarInt() throws IOException {
        final Encoder<Integer> encoder = (ob, v) -> ob.writeVarInt(v);
        final Decoder<Integer> decoder = (ib) -> ib.readVarInt();
        __assert(0, new byte[]{0}, encoder, decoder);
        __assert(Integer.MIN_VALUE, new byte[]{-128, -128, -128, -128, 8}, encoder, decoder);
        __assert(Integer.MAX_VALUE, new byte[]{-1, -1, -1, -1, 7}, encoder, decoder);
        __assert(0x7f, new byte[]{127}, encoder, decoder);
        __assert(0x40, new byte[]{64}, encoder, decoder);
        __assert(0x3fff, new byte[]{-1, 127}, encoder, decoder);
        __assert(0x2000, new byte[]{-128, 64}, encoder, decoder);
        __assert(0x1fffff, new byte[]{-1, -1, 127}, encoder, decoder);
        __assert(0x100000, new byte[]{-128, -128, 64}, encoder, decoder);
        __assert(0x0fffffff, new byte[]{-1, -1, -1, 127}, encoder, decoder);
        __assert(0x08000000, new byte[]{-128, -128, -128, 64}, encoder, decoder);
        __assert(0xffffffff, new byte[]{-1, -1, -1, -1, 15}, encoder, decoder);
    }

    @Test
    public void testVarLong() throws IOException {
        final Encoder<Long> encoder = (ob, v) -> ob.writeVarLong(v);
        final Decoder<Long> decoder = (ib) -> ib.readVarLong();
        __assert(0L, new byte[]{0}, encoder, decoder);
        __assert(Long.MIN_VALUE, new byte[]{-128, -128, -128, -128, -128, -128, -128, -128, -128}, encoder, decoder);
        __assert(Long.MAX_VALUE, new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, 127}, encoder, decoder);
        __assert(0x7fL, new byte[]{127}, encoder, decoder);
        __assert(0x40L, new byte[]{64}, encoder, decoder);
        __assert(0x3fffL, new byte[]{-1, 127}, encoder, decoder);
        __assert(0x2000L, new byte[]{-128, 64}, encoder, decoder);
        __assert(0x1fffffL, new byte[]{-1, -1, 127}, encoder, decoder);
        __assert(0x100000L, new byte[]{-128, -128, 64}, encoder, decoder);
        __assert(0x0fffffffL, new byte[]{-1, -1, -1, 127}, encoder, decoder);
        __assert(0x08000000L, new byte[]{-128, -128, -128, 64}, encoder, decoder);
        __assert(0x07ffffffffL, new byte[]{-1, -1, -1, -1, 127}, encoder, decoder);
        __assert(0x0400000000L, new byte[]{-128, -128, -128, -128, 64}, encoder, decoder);
        __assert(0x03ffffffffffL, new byte[]{-1, -1, -1, -1, -1, 127}, encoder, decoder);
        __assert(0x020000000000L, new byte[]{-128, -128, -128, -128, -128, 64}, encoder, decoder);
        __assert(0x01ffffffffffffL, new byte[]{-1, -1, -1, -1, -1, -1, 127}, encoder, decoder);
        __assert(0x01000000000000L, new byte[]{-128, -128, -128, -128, -128, -128, 64}, encoder, decoder);
        __assert(0xffffffffffffffL, new byte[]{-1, -1, -1, -1, -1, -1, -1, 127}, encoder, decoder);
        __assert(0x80000000000000L, new byte[]{-128, -128, -128, -128, -128, -128, -128, 64}, encoder, decoder);
        __assert(0xffffffffffffffffL, new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1}, encoder, decoder);
    }

    @Test
    public void testVarBinary() throws IOException {
        final Encoder<byte[]> encoder = (ob, v) -> ob.writeVarBinary(v);
        final Decoder<byte[]> decoder = (ib) -> ib.readVarBinary();
        __assert(new byte[]{}, new byte[]{0}, encoder, decoder);
        __assert(new byte[]{1}, new byte[]{1, 1}, encoder, decoder);
        __assert(new byte[]{1, 2, 3, 4, 5}, new byte[]{5, 1, 2, 3, 4, 5}, encoder, decoder);
    }

    @Test
    public void testVarString() throws IOException {
        final Encoder<String> encoder = (ob, v) -> ob.writeVarString(v);
        final Decoder<String> decoder = (ib) -> ib.readVarString();
        __assert("", new byte[]{0}, encoder, decoder);
        __assert("abcdefg", new byte[]{7, 97, 98, 99, 100, 101, 102, 103}, encoder, decoder);
    }

    private <V> void __assert(V v, byte[] b, Encoder<V> encoder, Decoder<V> decoder) throws IOException {
        // encode
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        encoder.encode(new VarIntOutputBuffer(os), v);
        Assert.assertEquals(os.toByteArray(), b);
        // decode
        ByteArrayInputStream is = new ByteArrayInputStream(b);
        Assert.assertEquals(decoder.decode(new VarIntInputBuffer(is)), v);
    }

    private interface Encoder<V> {
        void encode(VarIntOutputBuffer ob, V v) throws IOException;
    }

    private interface Decoder<V> {
        V decode(VarIntInputBuffer ib) throws IOException;
    }
}
