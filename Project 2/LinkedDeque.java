import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    // CODE HERE
    private Node first;
    private Node last;
    private int size;

    // Helper doubly-linked list class.
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // Construct an empty deque.
    public LinkedDeque() {
        // CODE HERE
        first = null; // CHANGE
        last = null; // CHANGE
        size = 0; // CHANGE
    }

    // Is the dequeue empty?
    public boolean isEmpty() {
        // CODE HERE
        return size == 0;
    }

    // The number of items on the deque.
    public int size() {
        // CODE HERE
        return size;
    }

    // Add item to the front of the deque.
    public void addFirst(Item item) {
        // CODE HERE
        if (item == null) throw new NullPointerException();

        Node previousFirst = first;
        first = new Node();
        first.item = item;
        first.next = previousFirst;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        }
        else previousFirst.prev = first;
        size++;
    }

    // Add item to the end of the deque.
    public void addLast(Item item) {
        // CODE HERE
        if (item == null) throw new NullPointerException();

        Node previousLast = last;

        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = previousLast;

        if (isEmpty()) {
            first = last;
        }
        else previousLast.next = last;
        size++;
    }

    // Remove and return item from the front of the deque.
    public Item removeFirst() {
        // CODE HERE
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    // Remove and return item from the end of the deque.
    public Item removeLast() {
        // CODE HERE
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        if (size == 1) {
            last = null;
            first = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<Item> iterator() {
        // CODE HERE
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        // CODE HERE
        private Node current;

        DequeIterator() {
            // CODE HERE
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            // CODE HERE
            if (!hasNext())
                throw new NoSuchElementException();
            Item var = current.item;
            current = current.next;
            return var;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            }
            else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
