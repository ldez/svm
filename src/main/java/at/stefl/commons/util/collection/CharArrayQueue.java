package at.stefl.commons.util.collection;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

// TODO: improve exceptions
// TODO: implement Deque
// TODO: rename to CharArrayDeque
public class CharArrayQueue extends AbstractQueue<Character> implements RandomAccess, CharSequence, Serializable {

    private static final long serialVersionUID = -795960019807037815L;

    private final char[] buffer;
    private int head;
    private int size;

    public CharArrayQueue(final int capacity) {
        this.buffer = new char[capacity];
    }

    @Override
    public String toString() {
        final char[] cbuf = new char[this.size];

        final int tmp = Math.min(this.size, this.buffer.length - this.head);
        System.arraycopy(this.buffer, this.head, cbuf, 0, tmp);
        if (tmp < this.size) {
            System.arraycopy(this.buffer, 0, cbuf, tmp, this.size - tmp);
        }

        return new String(cbuf);
    }

    private int index(final int i) {
        return (this.head + i) % this.buffer.length;
    }

    public char get(final int i) {
        if (i >= this.size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }

        return this.buffer[this.index(i)];
    }

    public void put(final char c) {
        this.buffer[this.index(this.size)] = c;
        if (this.size < this.buffer.length) {
            this.size++;
        } else {
            this.head = this.index(1);
        }
    }

    @Override
    public boolean offer(final Character e) {
        return this.offer(e);
    }

    public boolean offer(final char c) {
        if (this.buffer.length <= this.size) {
            return false;
        }

        this.buffer[this.index(this.size)] = c;
        this.size++;

        return true;
    }

    @Override
    public boolean add(final Character e) {
        return this.add(e.charValue());
    }

    public boolean add(final char c) {
        if (!this.offer(c)) {
            throw new IllegalStateException("queue is out of space");
        }
        return true;
    }

    public boolean addAll(final char[] cbuf) {
        return this.addAll(cbuf, 0, cbuf.length);
    }

    public boolean addAll(final char[] cbuf, final int off, final int len) {
        if (off < 0) {
            throw new IndexOutOfBoundsException("offset is negative");
        }
        if (len < 0) {
            throw new IndexOutOfBoundsException("length is negative");
        }
        if ((off + len) > cbuf.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len > (this.buffer.length - this.size)) {
            throw new IndexOutOfBoundsException("given buffer is to big");
        }
        if (len == 0) {
            return false;
        }

        final int tmp = Math.min(len, this.buffer.length - this.index(this.size));
        System.arraycopy(cbuf, off, this.buffer, this.index(this.size), tmp);
        if (len > (this.buffer.length - this.index(this.size))) {
            System.arraycopy(cbuf, off + tmp, this.buffer, 0, len - tmp);
        }
        this.size += len;

        return true;
    }

    public boolean addAll(final CharSequence charSequence) {
        if (charSequence.length() > (this.buffer.length - this.size)) {
            throw new IllegalStateException("given char sequence is to big");
        }
        if (charSequence.length() == 0) {
            return false;
        }

        for (int i = 0; i < charSequence.length(); i++) {
            this.add(charSequence.charAt(i));
        }

        return true;
    }

    @Override
    public Character poll() {
        final Character result = this.peek();
        if (result == null) {
            return null;
        }

        this.head = this.index(1);
        this.size--;

        return result;
    }

    @Override
    public Character remove() {
        return this.removeChar();
    }

    public char removeChar() {
        if (this.size <= 0) {
            throw new NoSuchElementException();
        }

        final char result = this.buffer[this.head];
        this.head = this.index(1);
        this.size--;

        return result;
    }

    @Override
    public Character peek() {
        if (this.size <= 0) {
            return null;
        }

        return this.buffer[this.head];
    }

    @Override
    public Character element() {
        return this.elementChar();
    }

    public char elementChar() {
        if (this.size <= 0) {
            throw new NoSuchElementException();
        }

        return this.buffer[this.head];
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {

            private int i;

            @Override
            public boolean hasNext() {
                return this.i < CharArrayQueue.this.size;
            }

            @Override
            public Character next() {
                return CharArrayQueue.this.buffer[CharArrayQueue.this.index(this.i++)];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove not supported");
            }
        };
    }

    @Override
    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.buffer.length;
    }

    @Override
    public void clear() {
        this.head = 0;
        this.size = 0;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public char charAt(final int index) {
        if (index >= this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return this.buffer[this.index(index)];
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        if (start > end) {
            throw new IndexOutOfBoundsException("start is higher than end");
        }
        if (start >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (end > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (start == end) {
            return new CharArrayQueue(0);
        }

        final int size = end - start;
        final CharArrayQueue result = new CharArrayQueue(size);

        final int tmp = Math.min(size, this.buffer.length - this.head);
        System.arraycopy(this.buffer, this.index(start), result.buffer, 0, tmp);
        if (tmp < size) {
            System.arraycopy(this.buffer, 0, result.buffer, tmp, size - tmp);
        }
        result.size = size;

        return result;
    }

}