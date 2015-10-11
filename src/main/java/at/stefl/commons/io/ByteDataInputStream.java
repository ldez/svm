package at.stefl.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class ByteDataInputStream extends DelegationInputStream {

    private final byte[] maxUnit = Endianness.getMaxUnitBuffer();

    private Endianness endianness;

    public ByteDataInputStream(final InputStream in) {
        this(in, Endianness.getNative());
    }

    public ByteDataInputStream(final InputStream in, final Endianness endianness) {
        super(in);

        this.endianness = endianness;
    }

    public Endianness getEndianness() {
        return this.endianness;
    }

    public void setEndianness(final Endianness endianness) {
        this.endianness = endianness;
    }

    public byte[] readFully(final int len) throws IOException {
        return ByteStreamUtil.readFully(this.in, len);
    }

    public void readFully(final byte[] b) throws IOException {
        ByteStreamUtil.readFully(this.in, b);
    }

    public void readFully(final byte[] b, final int off, final int len) throws IOException {
        ByteStreamUtil.readFully(this.in, b, off, len);
    }

    public byte[] readUnits(final int unit, final int len) throws IOException {
        final byte[] result = ByteStreamUtil.readFully(this.in, len);
        Endianness.swap(unit, result);
        return result;
    }

    public void readUnits(final int unit, final byte[] b) throws IOException {
        ByteStreamUtil.readFully(this.in, b);
        Endianness.swap(unit, b);
    }

    public void readUnits(final int unit, final byte[] b, final int off, final int len) throws IOException {
        ByteStreamUtil.readFully(this.in, b, off, len);
        Endianness.swap(unit, b, off, len);
    }

    private void readUnit(final int size) throws IOException {
        ByteStreamUtil.readFully(this.in, this.maxUnit, 0, size);
    }

    public boolean readBoolean() throws IOException {
        final int read = this.in.read();
        if (read == -1) {
            throw new EOFException();
        }
        return read != 0;
    }

    public byte readByte() throws IOException {
        final int read = this.in.read();
        if (read == -1) {
            throw new EOFException();
        }
        return (byte) read;
    }

    public short readUnsignedByte() throws IOException {
        return (short) (this.readByte() & 0xff);
    }

    public char readChar() throws IOException {
        this.readUnit(2);
        return this.endianness.getAsChar(this.maxUnit);
    }

    public short readShort() throws IOException {
        this.readUnit(2);
        return this.endianness.getAsShort(this.maxUnit);
    }

    public int readUnsignedShort() throws IOException {
        return this.readShort() & 0xffff;
    }

    public int readInt() throws IOException {
        this.readUnit(4);
        return this.endianness.getAsInt(this.maxUnit);
    }

    public long readUnsignedInt() throws IOException {
        return this.readInt() & 0xffffffffl;
    }

    public long readLong() throws IOException {
        this.readUnit(8);
        return this.endianness.getAsLong(this.maxUnit);
    }

}