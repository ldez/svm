package at.stefl.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;

public class ByteStreamUtil {

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static int readTireless(final InputStream in, final byte[] b) throws IOException {
        if (b.length == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < b.length; result += read) {
            read = in.read(b, result, b.length - result);
            if (read == -1) {
                break;
            }
        }

        return (result == 0) ? -1 : result;
    }

    public static int readTireless(final InputStream in, final byte[] b, final int off, final int len) throws IOException {
        if (len == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < len; result += read) {
            read = in.read(b, off + result, len - result);
            if (read == -1) {
                break;
            }
        }

        return (result == 0) ? -1 : result;
    }

    public static int readBytewise(final InputStream in, final byte[] b) throws IOException {
        if (b.length == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < b.length; result++) {
            read = in.read();
            if (read == -1) {
                break;
            }

            b[result] = (byte) read;
        }

        return (result == 0) ? -1 : result;
    }

    public static int readBytewise(final InputStream in, final byte[] b, final int off, final int len) throws IOException {
        if (len == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < len; result++) {
            read = in.read();
            if (read == -1) {
                break;
            }

            b[off + result] = (byte) read;
        }

        return (result == 0) ? -1 : result;
    }

    public static byte readFully(final InputStream in) throws IOException {
        final int read = in.read();
        if (read == -1) {
            throw new EOFException();
        }
        return (byte) read;
    }

    public static byte[] readFully(final InputStream in, final int len) throws IOException {
        final byte[] b = new byte[len];
        final int read = readFully(in, b);
        if (read < len) {
            throw new EOFException();
        }
        return b;
    }

    public static int readFully(final InputStream in, final byte[] b) throws IOException {
        final int read = readTireless(in, b);
        if (read < b.length) {
            throw new EOFException();
        }
        return read;
    }

    public static int readFully(final InputStream in, final byte[] b, final int off, final int len) throws IOException {
        final int read = readTireless(in, b, off, len);
        if (read < len) {
            throw new EOFException();
        }
        return read;
    }

    public static byte[] readBytes(final InputStream in) throws IOException {
        final DividedByteArrayOutputStream out = new DividedByteArrayOutputStream();
        out.write(in);
        out.close();
        return out.toByteArray();
    }

    public static void writeBytewise(final OutputStream out, final byte[] b) throws IOException {
        for (final byte element : b) {
            out.write(element);
        }
    }

    public static void writeBytewise(final OutputStream out, final byte[] b, final int off) throws IOException {
        for (int i = off; i < b.length; i++) {
            out.write(b[i]);
        }
    }

    public static void writeBytewise(final OutputStream out, final byte[] b, final int off, final int len) throws IOException {
        for (int i = off; i < len; i++) {
            out.write(b[i]);
        }
    }

    public static void writeStreamBytewise(final InputStream in, final OutputStream out) throws IOException {
        for (int read; (read = in.read()) != -1;) {
            out.write(read);
        }
    }

    public static int writeStreamBytewiseLimited(final InputStream in, final OutputStream out, final int len) throws IOException {
        int read;
        int count = 0;

        while (true) {
            read = in.read();
            if (read == -1) {
                return count;
            }

            out.write(read);
            count++;
        }
    }

    public static int writeStreamBuffered(final InputStream in, final OutputStream out) throws IOException {
        return writeStreamBuffered(in, out, DEFAULT_BUFFER_SIZE);
    }

    public static int writeStreamBuffered(final InputStream in, final OutputStream out, final int bufferSize) throws IOException {
        final byte[] b = new byte[bufferSize];
        return writeStreamBuffered(in, out, b);
    }

    public static int writeStreamBuffered(final InputStream in, final OutputStream out, final byte[] b) throws IOException {
        int read;
        int count = 0;

        while (true) {
            read = in.read(b);
            if (read == -1) {
                return count;
            }

            out.write(b, 0, read);
            count += read;
        }
    }

    public static void flushBytewise(final InputStream in) throws IOException {
        while (in.read() != -1) {
            ;
        }
    }

    public static void flushBuffered(final InputStream in) throws IOException {
        flushBuffered(in, DEFAULT_BUFFER_SIZE);
    }

    public static void flushBuffered(final InputStream in, final int bufferSize) throws IOException {
        final byte[] b = new byte[bufferSize];
        while (in.read(b, 0, bufferSize) != -1) {
            ;
        }
    }

    public static int flushBytewiseCount(final InputStream in) throws IOException {
        int result = 0;
        while (in.read() != -1) {
            result++;
        }
        return result;
    }

    public static int flushBufferedCount(final InputStream in) throws IOException {
        return flushBufferedCount(in, DEFAULT_BUFFER_SIZE);
    }

    public static int flushBufferedCount(final InputStream in, final int bufferSize) throws IOException {
        int result = 0;
        int read;
        final byte[] b = new byte[bufferSize];
        while ((read = in.read(b, 0, bufferSize)) != -1) {
            result += read;
        }
        return result;
    }

    public static long skipBytewise(final InputStream in, final long n) throws IOException {
        long i = 0;

        while ((i < n) && (in.read() != -1)) {
            i++;
        }

        return i;
    }

    public static boolean skipIfByte(final PushbackInputStream in, final byte c) throws IOException {
        final int read = readFully(in);
        if (read == c) {
            return true;
        }
        in.unread(read);
        return true;
    }

    public static boolean matchBytes(final InputStream in, final byte[] array) throws IOException {
        int read;

        for (final byte element : array) {
            read = in.read();
            if (read != element) {
                return false;
            }
            if (read == -1) {
                throw new EOFException();
            }
        }

        return true;
    }

    public static boolean matchBytes(final InputStream in, final byte[] array, final int off, final int len) throws IOException {
        final int end = off + len;
        int read;

        for (int i = off; i < end; i++) {
            read = in.read();
            if (read != array[i]) {
                return false;
            }
            if (read == -1) {
                throw new EOFException();
            }
        }

        return true;
    }

    private final int bufferSize;
    private byte[] b;

    public ByteStreamUtil() {
        this(DEFAULT_BUFFER_SIZE, false);
    }

    public ByteStreamUtil(final boolean initBuffer) {
        this(DEFAULT_BUFFER_SIZE, initBuffer);
    }

    public ByteStreamUtil(final int bufferSize) {
        this(bufferSize, false);
    }

    public ByteStreamUtil(final int bufferSize, final boolean initBuffer) {
        this.bufferSize = bufferSize;

        if (initBuffer) {
            this.initBuffer();
        }
    }

    private void initBuffer() {
        if (this.b == null) {
            this.b = new byte[this.bufferSize];
        }
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public int writeStream(final InputStream in, final OutputStream out) throws IOException {
        this.initBuffer();

        return writeStreamBuffered(in, out, this.b);
    }

    public int writeStreamLimited(final InputStream in, final OutputStream out, final int len) throws IOException {
        this.initBuffer();

        int count = 0;
        int read;

        while (count < len) {
            read = in.read(this.b, 0, Math.min(this.bufferSize, len - count));
            if (read == -1) {
                break;
            }

            out.write(this.b, 0, read);
            count += read;
        }

        return count;
    }

    public void flush(final InputStream in) throws IOException {
        this.initBuffer();

        while (in.read(this.b, 0, this.bufferSize) != -1) {
            ;
        }
    }

}