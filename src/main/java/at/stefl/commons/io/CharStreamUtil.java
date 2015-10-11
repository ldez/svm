package at.stefl.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.stefl.commons.util.collection.CharArrayQueue;
import at.stefl.commons.util.string.CharSequenceUtil;

// TODO: kill StringBuilder
public class CharStreamUtil {

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static int readTireless(final Reader in, final char[] cbuf) throws IOException {
        if (cbuf.length == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < cbuf.length; result += read) {
            read = in.read(cbuf, result, cbuf.length - result);
            if (read == -1) {
                break;
            }
        }

        return (result == 0) ? -1 : result;
    }

    public static int readTireless(final Reader in, final char[] cbuf, final int off, final int len) throws IOException {
        if (cbuf.length == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < len; result += read) {
            read = in.read(cbuf, off + result, len - result);
            if (read == -1) {
                break;
            }
        }

        return (result == 0) ? -1 : result;
    }

    public static int readTireless(final Reader in, final CharBuffer target) throws IOException {
        final int len = target.remaining();
        if (len == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < len; result += read) {
            read = in.read(target);
            if (read == -1) {
                break;
            }
        }

        return (result == 0) ? -1 : result;
    }

    public static int readCharwise(final Reader in, final char[] cbuf) throws IOException {
        if (cbuf.length == 0) {
            return 0;
        }

        int result;
        int read;

        for (result = 0; result < cbuf.length; result++) {
            read = in.read();
            if (read == -1) {
                break;
            }

            cbuf[result] = (char) read;
        }

        return (result == 0) ? -1 : result;
    }

    public static int readCharwise(final Reader in, final char[] cbuf, final int off, final int len) throws IOException {
        int result;
        int read;

        for (result = 0; result < cbuf.length; result++) {
            read = in.read();
            if (read == -1) {
                break;
            }

            cbuf[off + result] = (char) read;
        }

        return (result == 0) ? -1 : result;
    }

    public static int readCharwise(final Reader in, final CharBuffer target) throws IOException {
        final int len = target.remaining();
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

            target.put((char) read);
        }

        return (result == 0) ? -1 : result;
    }

    public static char readFully(final Reader in) throws IOException {
        final int read = in.read();
        if (read == -1) {
            throw new EOFException();
        }
        return (char) read;
    }

    public static char[] readFully(final Reader in, final int len) throws IOException {
        final char[] cbuf = new char[len];
        final int read = readFully(in, cbuf);
        if (read < len) {
            throw new EOFException();
        }
        return cbuf;
    }

    public static int readFully(final Reader in, final char[] cbuf) throws IOException {
        final int read = readTireless(in, cbuf);
        if (read < cbuf.length) {
            throw new EOFException();
        }
        return read;
    }

    public static int readFully(final Reader in, final char[] cbuf, final int off, final int len) throws IOException {
        final int read = readTireless(in, cbuf, off, len);
        if (read < len) {
            throw new EOFException();
        }
        return read;
    }

    public static int readFully(final Reader in, final CharBuffer target) throws IOException {
        final int remaining = target.remaining();
        final int read = readTireless(in, target);
        if (read < remaining) {
            throw new EOFException();
        }
        return read;
    }

    public static char[] readBytes(final Reader in) throws IOException {
        final DividedCharArrayWriter out = new DividedCharArrayWriter();
        out.write(in);
        out.close();
        return out.toCharArray();
    }

    public static String readString(final Reader in) throws IOException {
        final DividedCharArrayWriter out = new DividedCharArrayWriter();
        out.write(in);
        out.close();
        return out.toString();
    }

    public static String readLine(final PushbackReader in) throws IOException {
        @SuppressWarnings("resource")
        final DividedCharArrayWriter out = new DividedCharArrayWriter();
        int read;

        while (true) {
            read = in.read();

            switch (read) {
                case '\r':
                    skipIfChar(in, '\n');
                case '\n':
                case -1:
                    return out.isEmpty() ? null : out.toString();
                default:
                    out.write(read);
            }
        }
    }

    public static String readUntilChar(final Reader in, final char c) throws IOException {
        @SuppressWarnings("resource")
        final DividedCharArrayWriter out = new DividedCharArrayWriter();
        int read;

        while (true) {
            read = in.read();
            if (read == c) {
                return out.toString();
            }
            if (read == -1) {
                throw new EOFException();
            }

            out.write((char) read);
        }
    }

    public static String readUntilChar(final Reader in, final Set<Character> chars) throws IOException {
        @SuppressWarnings("resource")
        final DividedCharArrayWriter out = new DividedCharArrayWriter();
        int read;

        while (true) {
            read = in.read();
            if (chars.contains((char) read)) {
                return out.toString();
            }
            if (read == -1) {
                throw new EOFException();
            }

            out.write((char) read);
        }
    }

    // TODO: improve
    public static String readUntilString(final Reader in, final String string) throws IOException {
        final StringBuilder builder = new StringBuilder();
        int read;

        while ((read = in.read()) != -1) {
            builder.append((char) read);
            if ((builder.length() >= string.length()) && CharSequenceUtil.endsWith(builder, string)) {
                return builder.substring(0, builder.length() - string.length());
            }
        }

        throw new EOFException();
    }

    public static void writeCharwise(final Writer out, final char[] cbuf) throws IOException {
        for (final char element : cbuf) {
            out.write(element);
        }
    }

    public static void writeCharwise(final Writer out, final char[] cbuf, final int off) throws IOException {
        for (int i = off; i < cbuf.length; i++) {
            out.write(cbuf[i]);
        }
    }

    public static void writeCharwise(final Writer out, final char[] cbuf, final int off, final int len) throws IOException {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            out.write(cbuf[i]);
        }
    }

    public static void writeCharwise(final Writer out, final String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            out.write(str.charAt(i));
        }
    }

    public static void writeCharwise(final Writer out, final String str, final int off) throws IOException {
        for (int i = off; i < str.length(); i++) {
            out.write(str.charAt(i));
        }
    }

    public static void writeCharwise(final Writer out, final String str, final int off, final int len) throws IOException {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            out.write(str.charAt(i));
        }
    }

    public static void appendCharwise(final Writer out, CharSequence csq) throws IOException {
        if (csq == null) {
            csq = CharSequenceUtil.NULL;
        }

        final int len = csq.length();

        for (int i = 0; i < len; i++) {
            out.append(csq.charAt(i));
        }
    }

    public static void appendCharwise(final Writer out, CharSequence csq, final int start) throws IOException {
        if (csq == null) {
            csq = CharSequenceUtil.NULL;
        }

        final int len = csq.length();

        for (int i = start; i < len; i++) {
            out.append(csq.charAt(i));
        }
    }

    public static void appendCharwise(final Writer out, CharSequence csq, int start, int end) throws IOException {
        if (csq == null) {
            csq = CharSequenceUtil.NULL;
            start = 0;
            end = CharSequenceUtil.NULL.length();
        }

        for (int i = start; i < end; i++) {
            out.append(csq.charAt(i));
        }
    }

    public static void writeStreamCharwise(final Reader in, final Writer out) throws IOException {
        for (int read; (read = in.read()) != -1;) {
            out.write(read);
        }
    }

    public static int writeStreamCharwiseLimited(final Reader in, final Writer out, final int len) throws IOException {
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

    public static int writeStreamBuffered(final Reader in, final Writer out) throws IOException {
        return writeStreamBuffered(in, out, DEFAULT_BUFFER_SIZE);
    }

    public static int writeStreamBuffered(final Reader in, final Writer out, final int bufferSize) throws IOException {
        final char[] cbuf = new char[bufferSize];
        return writeStreamBuffered(in, out, cbuf);
    }

    public static int writeStreamBuffered(final Reader in, final Writer out, final char[] cbuf) throws IOException {
        int read;
        int count = 0;

        while (true) {
            read = in.read(cbuf);
            if (read == -1) {
                return count;
            }

            out.write(cbuf, 0, read);
            count += read;
        }
    }

    public static void flushCharwise(final Reader in) throws IOException {
        while (in.read() != -1) {
            ;
        }
    }

    public static void flushBuffered(final Reader in) throws IOException {
        flushBuffered(in, DEFAULT_BUFFER_SIZE);
    }

    public static void flushBuffered(final Reader in, final int bufferSize) throws IOException {
        final char[] cbuf = new char[bufferSize];
        while (in.read(cbuf, 0, bufferSize) != -1) {
            ;
        }
    }

    public static int flushBytewiseCount(final Reader in) throws IOException {
        int result = 0;
        while (in.read() != -1) {
            result++;
        }
        return result;
    }

    public static int flushBufferedCount(final Reader in) throws IOException {
        return flushBufferedCount(in, DEFAULT_BUFFER_SIZE);
    }

    public static int flushBufferedCount(final Reader in, final int bufferSize) throws IOException {
        int result = 0;
        int read;
        final char[] cbuf = new char[bufferSize];
        while ((read = in.read(cbuf, 0, bufferSize)) != -1) {
            result += read;
        }
        return result;
    }

    public static void flushLine(final PushbackReader in) throws IOException {
        while (true) {
            switch (in.read()) {
                case '\r':
                    skipIfChar(in, '\n');
                case '\n':
                case -1:
                    return;
                default:
                    break;
            }
        }
    }

    public static int flushWhitespace(final Reader in) throws IOException {
        int read;

        while (true) {
            read = in.read();
            if (!Character.isWhitespace(read)) {
                break;
            }
            if (read == -1) {
                break;
            }
        }

        return read;
    }

    public static void flushWhitespace(final PushbackReader in) throws IOException {
        final int read = flushWhitespace((Reader) in);
        if (read != -1) {
            in.unread(read);
        }
    }

    public static int flushChars(final Reader in, final char c) throws IOException {
        int read;

        while (true) {
            read = in.read();
            if (read != c) {
                break;
            }
            if (read == -1) {
                break;
            }
        }

        return read;
    }

    public static void flushChars(final PushbackReader in, final char c) throws IOException {
        final int read = flushChars((Reader) in, c);
        if (read != -1) {
            in.unread(read);
        }
    }

    public static int flushUntilFilter(final Reader in, final CharFilter filter) throws IOException {
        int read;

        while (true) {
            read = in.read();
            if (filter.accept((char) read)) {
                break;
            }
            if (read == -1) {
                break;
            }
        }

        return read;
    }

    public static void flushUntilFilter(final PushbackReader in, final CharFilter filter) throws IOException {
        final int read = flushUntilFilter((Reader) in, filter);
        if (read == -1) {
            throw new EOFException();
        }
        in.unread(read);
    }

    public static void flushUntilChar(final Reader in, final char c) throws IOException {
        int read;

        while (true) {
            read = in.read();
            if (read == -1) {
                throw new EOFException();
            }
            if (read == c) {
                break;
            }
        }
    }

    // TODO: improve
    public static void flushUntilString(final Reader in, final String string) throws IOException {
        final CharArrayQueue queue = new CharArrayQueue(string.length());
        int read;

        while (true) {
            if (CharSequenceUtil.equals(queue, string)) {
                return;
            }
            read = in.read();
            if (read == -1) {
                break;
            }
            queue.put((char) read);
        }

        throw new EOFException();
    }

    // TODO: improve
    public static Matcher flushUntilMatch(final Reader in, final Pattern pattern) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final Matcher matcher = pattern.matcher(builder);
        int read;

        while (true) {
            read = in.read();
            if (read == -1) {
                break;
            }

            builder.append((char) read);

            matcher.reset();
            if (matcher.matches()) {
                return matcher;
            }
        }

        throw new EOFException();
    }

    // TODO: improve
    public static Matcher flushUntilFind(final Reader in, final Pattern pattern) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final Matcher matcher = pattern.matcher(builder);
        int read;

        while (true) {
            read = in.read();
            if (read == -1) {
                break;
            }

            builder.append((char) read);

            matcher.reset();
            if (matcher.find()) {
                return matcher;
            }
        }

        throw new EOFException();
    }

    public static long skipCharwise(final Reader in, final long n) throws IOException {
        long i = 0;

        while ((i < n) && (in.read() != -1)) {
            i++;
        }

        return i;
    }

    public static boolean skipIfChar(final PushbackReader in, final char c) throws IOException {
        final int read = readFully(in);
        if (read == c) {
            return true;
        }
        in.unread(read);
        return true;
    }

    public static boolean matchChars(final Reader in, final char[] array) throws IOException {
        int read;

        for (final char element : array) {
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

    public static boolean matchChars(final Reader in, final char[] array, final int off) throws IOException {
        int read;

        for (int i = off; i < array.length; i++) {
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

    public static boolean matchChars(final Reader in, final char[] array, final int off, final int len) throws IOException {
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

    public static boolean matchChars(final Reader in, final CharSequence charSequence) throws IOException {
        final int length = charSequence.length();
        int read;

        for (int i = 0; i < length; i++) {
            read = in.read();
            if (read != charSequence.charAt(i)) {
                return false;
            }
            if (read == -1) {
                throw new EOFException();
            }
        }

        return true;
    }

    public static boolean matchChars(final Reader in, final CharSequence charSequence, final int start, final int end) throws IOException {
        int read;

        for (int i = start; i < end; i++) {
            read = in.read();
            if (read != charSequence.charAt(i)) {
                return false;
            }
            if (read == -1) {
                throw new EOFException();
            }
        }

        return true;
    }

    private final int bufferSize;
    private char[] cbuf;

    public CharStreamUtil() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public CharStreamUtil(final boolean initBuffer) {
        this(DEFAULT_BUFFER_SIZE, false);
    }

    public CharStreamUtil(final int bufferSize) {
        this(bufferSize, false);
    }

    public CharStreamUtil(final int bufferSize, final boolean initBuffer) {
        this.bufferSize = bufferSize;

        if (initBuffer) {
            this.initBuffer();
        }
    }

    private void initBuffer() {
        if (this.cbuf == null) {
            this.cbuf = new char[this.bufferSize];
        }
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public int writeStream(final Reader in, final Writer out) throws IOException {
        this.initBuffer();

        int read;
        int count = 0;

        while (true) {
            read = in.read(this.cbuf);
            if (read == -1) {
                return count;
            }

            out.write(this.cbuf, 0, read);
            count += read;
        }
    }

    public int writeStreamLimited(final Reader in, final Writer out, final int len) throws IOException {
        this.initBuffer();

        int count = 0;
        int read;

        while (count < len) {
            read = in.read(this.cbuf, 0, Math.min(this.bufferSize, len - count));
            if (read == -1) {
                break;
            }

            out.write(this.cbuf, 0, read);
            count += read;
        }

        return count;
    }

    public void flush(final Reader in) throws IOException {
        this.initBuffer();

        while (in.read(this.cbuf, 0, this.bufferSize) != -1) {
            ;
        }
    }

}