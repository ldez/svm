package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class ByteDataOutputStream extends DelegationOutputStream {

    private final byte[] maxUnit = Endianness.getMaxUnitBuffer();

    private Endianness endianness;

    public ByteDataOutputStream(final OutputStream out) {
        this(out, Endianness.getNative());
    }

    public ByteDataOutputStream(final OutputStream out, final Endianness endianness) {
        super(out);

        this.endianness = endianness;
    }

    public Endianness getEndianness() {
        return this.endianness;
    }

    public void setEndianness(final Endianness endianness) {
        this.endianness = endianness;
    }

    // TODO: improve?
    public void writeUnits(final int unit, byte[] b) throws IOException {
        b = b.clone();
        Endianness.swap(unit, b);
        this.write(b);
    }

    // TODO: improve?
    public void writeUnits(final int unit, byte[] b, final int off, final int len) throws IOException {
        b = Arrays.copyOfRange(b, off, off + len);
        Endianness.swap(unit, b);
        this.write(b);
    }

    private void writeUnit(final int size) throws IOException {
        this.out.write(this.maxUnit, 0, size);
    }

    public void writeBoolean(final boolean v) throws IOException {
        this.out.write(v ? 1 : 0);
    }

    public void writeByte(final byte v) throws IOException {
        this.out.write(v);
    }

    public void writeUnsignedByte(final short v) throws IOException {
        this.writeByte((byte) v);
    }

    public void writeChar(final char v) throws IOException {
        this.endianness.getBytes(v, this.maxUnit);
        this.writeUnit(2);
    }

    public void writeShort(final short v) throws IOException {
        this.endianness.getBytes(v, this.maxUnit);
        this.writeUnit(2);
    }

    public void writeUnsignedShort(final int v) throws IOException {
        this.writeShort((short) v);
    }

    public void writeInt(final int v) throws IOException {
        this.endianness.getBytes(v, this.maxUnit);
        this.writeUnit(4);
    }

    public void writeUnsignedInt(final long v) throws IOException {
        this.writeInt((int) v);
    }

    public void writeLong(final long v) throws IOException {
        this.endianness.getBytes(v, this.maxUnit);
        this.writeUnit(8);
    }

}