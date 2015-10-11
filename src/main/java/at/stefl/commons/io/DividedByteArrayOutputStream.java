package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import at.stefl.commons.util.array.ArrayUtil;
import at.stefl.commons.util.collection.SingleLinkedNode;

// TODO: implement better solution (-> growable array)
public class DividedByteArrayOutputStream extends OutputStream {

    private static final int DEFAULT_INITIAL_SIZE = 16;

    private class ConcatInputStream extends InputStream {
        private SingleLinkedNode<byte[]> currentNode;
        private byte[] currentBuffer;
        private int currentIndex;

        private int position;

        private final int revision;

        private ConcatInputStream() {
            this.currentNode = DividedByteArrayOutputStream.this.headNode;
            this.currentBuffer = this.currentNode.getEntry();
            this.revision = DividedByteArrayOutputStream.this.revision;
        }

        private void checkRevision() {
            if (this.revision != DividedByteArrayOutputStream.this.revision) {
                throw new IllegalStateException("stream was reset");
            }
        }

        private boolean ensureBuffer() {
            if (this.currentIndex >= this.currentBuffer.length) {
                if (!this.currentNode.hasNext()) {
                    return false;
                }
                this.currentNode = this.currentNode.getNext();
                this.currentBuffer = this.currentNode.getEntry();
                this.currentIndex = 0;
            }

            return true;
        }

        @Override
        public int available() throws IOException {
            this.checkRevision();
            return DividedByteArrayOutputStream.this.size - this.position;
        }

        @Override
        public int read() throws IOException {
            this.checkRevision();
            if (!this.ensureBuffer()) {
                return -1;
            }
            this.position++;
            return this.currentBuffer[this.currentIndex++];
        }

        @Override
        public int read(final byte[] b) throws IOException {
            return this.read(b, 0, b.length);
        }

        @Override
        public int read(final byte[] b, int off, int len) throws IOException {
            this.checkRevision();
            int read = 0;

            while (len > 0) {
                if (!this.ensureBuffer()) {
                    break;
                }
                final int part = Math.min(this.currentBuffer.length - this.currentIndex, len);
                System.arraycopy(this.currentBuffer, this.currentIndex, b, off, part);

                off += part;
                len -= part;
                this.currentIndex += part;
                read += part;
                this.position += part;
            }

            return (read == 0) ? -1 : read;
        }
    }

    private SingleLinkedNode<byte[]> headNode;
    private SingleLinkedNode<byte[]> currentNode;
    private byte[] currentBuffer;
    private int currentIndex;

    private int size;

    private int revision;

    public DividedByteArrayOutputStream() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public DividedByteArrayOutputStream(final int initialSize) {
        this.currentNode = this.headNode = new SingleLinkedNode<byte[]>();
        this.currentNode.setEntry(this.currentBuffer = new byte[initialSize]);
    }

    @Override
    public String toString() {
        return new String(this.toByteArray());
    }

    public String toString(final Charset charset) {
        return new String(this.toByteArray(), charset);
    }

    public String toString(final String charset) throws UnsupportedEncodingException {
        return new String(this.toByteArray(), charset);
    }

    public int size() {
        return this.size;
    }

    public byte[] toByteArray() {
        if (this.size == 0) {
            return ArrayUtil.EMPTY_BYTE_ARRAY;
        }

        final byte[] result = new byte[this.size];
        int index = 0;

        for (final byte[] buffer : this.headNode) {
            final int len = Math.min(buffer.length, this.size - index);
            if (len <= 0) {
                break;
            }
            System.arraycopy(buffer, 0, result, index, len);
            index += buffer.length;
        }

        return result;
    }

    public InputStream getInputStream() {
        return new ConcatInputStream();
    }

    private void ensureSpace(final int space) {
        if (this.currentIndex >= this.currentBuffer.length) {
            this.getMoreSpace(space);
        }
    }

    // TODO: improve buffer scaling
    private void getMoreSpace(final int space) {
        if (this.currentNode.hasNext()) {
            this.currentNode = this.currentNode.getNext();
            this.currentBuffer = this.currentNode.getEntry();
        } else {
            final int newSize = Math.max(this.currentBuffer.length << 1, space);
            this.currentNode = this.currentNode.append(new SingleLinkedNode<byte[]>());
            this.currentNode.setEntry(this.currentBuffer = new byte[newSize]);
        }

        this.currentIndex = 0;
    }

    @Override
    public void write(final int b) {
        this.ensureSpace(1);
        this.currentBuffer[this.currentIndex] = (byte) b;
        this.currentIndex++;
        this.size++;
    }

    @Override
    public void write(final byte[] b) {
        this.write(b, 0, b.length);
    }

    @Override
    public void write(final byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        if ((off < 0) || (len < 0) || (len > (b.length - off))) {
            throw new IndexOutOfBoundsException();
        }

        while (len > 0) {
            this.ensureSpace(len);
            final int part = Math.min(this.currentBuffer.length - this.currentIndex, len);
            System.arraycopy(b, off, this.currentBuffer, this.currentIndex, part);

            off += part;
            len -= part;
            this.currentIndex += part;
            this.size += part;
        }
    }

    // TODO: improve buffer scaling
    public int write(final InputStream in) throws IOException {
        final int lastCount = this.size;

        while (true) {
            final int read = in.read(this.currentBuffer, this.currentIndex, this.currentBuffer.length - this.currentIndex);
            if (read == -1) {
                break;
            }

            this.currentIndex += read;
            this.size += read;

            this.ensureSpace(this.currentBuffer.length);
        }

        return this.size - lastCount;
    }

    // TODO: improve buffer scaling
    public int write(final InputStream in, int len) throws IOException {
        final int lastCount = this.size;

        while (true) {
            final int part = Math.min(this.currentBuffer.length - this.currentIndex, len);
            final int read = in.read(this.currentBuffer, this.currentIndex, part);
            if (read == -1) {
                break;
            }

            len -= read;
            this.currentIndex += read;
            this.size += read;

            if (len > 0) {
                break;
            }

            this.ensureSpace(this.currentBuffer.length);
        }

        return this.size - lastCount;
    }

    public void writeTo(final OutputStream out) throws IOException {
        for (final byte[] buffer : this.headNode) {
            if (buffer == this.currentBuffer) {
                out.write(this.currentBuffer, 0, this.currentIndex);
                break;
            } else {
                out.write(buffer);
            }
        }
    }

    public void reset() {
        this.currentNode = this.headNode;
        this.currentBuffer = this.currentNode.getEntry();
        this.currentIndex = 0;
        this.size = 0;

        this.revision++;
    }

    @Override
    public void flush() {}

    @Override
    public void close() {}

}