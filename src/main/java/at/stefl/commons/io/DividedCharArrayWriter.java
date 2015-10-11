package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import at.stefl.commons.util.array.ArrayUtil;
import at.stefl.commons.util.collection.SingleLinkedNode;

// TODO: implement better solution (-> growable array)
public class DividedCharArrayWriter extends Writer {

    private static final int DEFAULT_INITIAL_SIZE = 16;

    private class ConcatReader extends Reader {
        private SingleLinkedNode<char[]> currentNode;
        private char[] currentBuffer;
        private int currentIndex;

        private int position;

        private final int revision;

        private ConcatReader() {
            this.currentNode = DividedCharArrayWriter.this.headNode;
            this.currentBuffer = this.currentNode.getEntry();
            this.revision = DividedCharArrayWriter.this.revision;
        }

        private void checkRevision() {
            if (this.revision != DividedCharArrayWriter.this.revision) {
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
        public boolean ready() {
            this.checkRevision();
            return this.position < DividedCharArrayWriter.this.size;
        }

        @Override
        public int read() throws IOException {
            this.checkRevision();
            if (!this.ensureBuffer()) {
                return -1;
            }
            return this.currentBuffer[this.currentIndex++];
        }

        @Override
        public int read(final char[] cbuf) throws IOException {
            return this.read(cbuf, 0, cbuf.length);
        }

        @Override
        public int read(final char[] cbuf, int off, int len) throws IOException {
            this.checkRevision();

            int read = 0;

            while (len > 0) {
                if (!this.ensureBuffer()) {
                    break;
                }
                final int part = Math.min(this.currentBuffer.length - this.currentIndex, len);
                System.arraycopy(this.currentBuffer, this.currentIndex, cbuf, off, part);

                off += part;
                len -= part;
                this.currentIndex += part;
                read += part;
            }

            return (read == 0) ? -1 : read;
        }

        @Override
        public void close() {}
    }

    private SingleLinkedNode<char[]> headNode;
    private SingleLinkedNode<char[]> currentNode;
    private char[] currentBuffer;
    private int currentIndex;

    private int size;

    private int revision;

    public DividedCharArrayWriter() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public DividedCharArrayWriter(final int initialSize) {
        this.currentNode = this.headNode = new SingleLinkedNode<char[]>();
        this.currentNode.setEntry(this.currentBuffer = new char[initialSize]);
    }

    @Override
    public String toString() {
        return new String(this.toCharArray());
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public char[] toCharArray() {
        if (this.size == 0) {
            return ArrayUtil.EMPTY_CHAR_ARRAY;
        }

        final char[] result = new char[this.size];
        int index = 0;

        for (final char[] buffer : this.headNode) {
            final int len = Math.min(buffer.length, this.size - index);
            if (len <= 0) {
                break;
            }
            System.arraycopy(buffer, 0, result, index, len);
            index += buffer.length;
        }

        return result;
    }

    public Reader getReader() {
        return new ConcatReader();
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
            this.currentNode = this.currentNode.append(new SingleLinkedNode<char[]>());
            this.currentNode.setEntry(this.currentBuffer = new char[newSize]);
        }

        this.currentIndex = 0;
    }

    @Override
    public void write(final int c) {
        this.ensureSpace(1);
        this.currentBuffer[this.currentIndex] = (char) c;
        this.currentIndex++;
        this.size++;
    }

    @Override
    public void write(final char[] cbuf) {
        this.write(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(final char[] cbuf, int off, int len) {
        if (cbuf == null) {
            throw new NullPointerException();
        }
        if ((off < 0) || (len < 0) || (len > (cbuf.length - off))) {
            throw new IndexOutOfBoundsException();
        }

        this.size += len;

        while (len > 0) {
            this.ensureSpace(len);
            final int part = Math.min(this.currentBuffer.length - this.currentIndex, len);
            System.arraycopy(cbuf, off, this.currentBuffer, this.currentIndex, part);

            off += part;
            len -= part;
            this.currentIndex += part;
        }
    }

    @Override
    public void write(final String str) {
        this.write(str, 0, str.length());
    }

    @Override
    public void write(final String str, int off, int len) {
        if (str == null) {
            throw new NullPointerException();
        }
        if ((off < 0) || (len < 0) || (len > (str.length() - off))) {
            throw new IndexOutOfBoundsException();
        }

        while (len > 0) {
            this.ensureSpace(len);
            final int part = Math.min(this.currentBuffer.length - this.currentIndex, len);
            str.getChars(off, off + part, this.currentBuffer, this.currentIndex);

            off += part;
            len -= part;
            this.currentIndex += part;
            this.size += part;
        }
    }

    public int write(final Reader in) throws IOException {
        final int lastCount = this.size;

        while (true) {
            final int read = in.read(this.currentBuffer, this.currentIndex, this.currentBuffer.length - this.currentIndex);
            if (read == -1) {
                break;
            }

            this.currentIndex += read;
            this.size += read;

            if (this.currentIndex >= this.currentBuffer.length) {
                this.getMoreSpace(this.currentBuffer.length);
            }
        }

        return this.size - lastCount;
    }

    public int write(final Reader in, int len) throws IOException {
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

    @Override
    public Writer append(final char c) {
        this.write(c);
        return this;
    }

    @Override
    public Writer append(final CharSequence csq) {
        return this.append(csq, 0, csq.length());
    }

    @Override
    public Writer append(CharSequence csq, final int start, final int end) {
        if ((start < 0) || (end < 0) || (start > end)) {
            throw new IndexOutOfBoundsException();
        }
        if (csq == null) {
            csq = "null";
        }

        for (int i = start; i <= end; i++) {
            this.ensureSpace(end - i + 1);
            this.currentBuffer[this.currentIndex] = csq.charAt(i);
            this.currentIndex++;
            this.size++;
        }

        return this;
    }

    public void writeTo(final Writer out) throws IOException {
        for (final char[] buffer : this.headNode) {
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