package at.stefl.commons.util.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SingleLinkedNode<E> implements Iterable<E> {

    public static <E> boolean hasCycle(final SingleLinkedNode<E> start) {
        final Set<SingleLinkedNode<E>> collected = new HashSet<SingleLinkedNode<E>>();
        SingleLinkedNode<E> currentNode = start;

        while (currentNode.hasNext()) {
            currentNode = currentNode.next;
            if (collected.contains(currentNode)) {
                return true;
            }
            collected.add(currentNode);
        }

        return false;
    }

    private class EntryIterator implements Iterator<E> {

        private SingleLinkedNode<E> currentNode = SingleLinkedNode.this;

        @Override
        public boolean hasNext() {
            return this.currentNode != null;
        }

        @Override
        public E next() {
            if (this.currentNode == null) {
                return null;
            }

            final E entry = this.currentNode.entry;
            this.currentNode = this.currentNode.next;

            return entry;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private E entry;
    private SingleLinkedNode<E> next;

    public SingleLinkedNode() {}

    public SingleLinkedNode(final E entry) {
        this.entry = entry;
    }

    public SingleLinkedNode(final E entry, final SingleLinkedNode<E> next) {
        this.entry = entry;
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public E getEntry() {
        return this.entry;
    }

    public SingleLinkedNode<E> getNext() {
        return this.next;
    }

    public void setEntry(final E entry) {
        this.entry = entry;
    }

    public void setNext(final SingleLinkedNode<E> next) {
        this.next = next;
    }

    public SingleLinkedNode<E> append(final SingleLinkedNode<E> next) {
        this.setNext(next);
        return next;
    }

    @Override
    public Iterator<E> iterator() {
        return new EntryIterator();
    }

}