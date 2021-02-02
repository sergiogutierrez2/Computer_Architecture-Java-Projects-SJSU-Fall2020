import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    // CODE HERE
    private Item[] items; // items of items
    private int size; // number of elements in RandomizedQueue

    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // Is the queue empty?
    public boolean isEmpty() {
        // CODE HERE
        return size == 0;
    }

    // The number of items on the queue.
    public int size() {
        // CODE HERE
        return size;
    }

    // Add item to the queue.
    public void enqueue(Item item) {
        // CODE HERE
        if (item == null)
            throw new NullPointerException();
        if (size() == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;

    }


    // Remove and return a random item from the queue.
    public Item dequeue() {
        // CODE HERE

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        Item item = items[index];
        items[index] = items[--size];
        items[size] = null;

        if (size() > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        // CODE HERE
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        return items[index];
    }

    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        // CODE HERE
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        // CODE HERE
        private ResizingArrayRandomQueue<Item> clone;


        RandomQueueIterator() {
            // CODE HERE
            clone = new ResizingArrayRandomQueue<Item>();

            for (int i = 0; i < size(); i++) {
                clone.enqueue(items[i]);
            }
        }

        public boolean hasNext() {
            // CODE HERE
            return clone.size() > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            // CODE HERE
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return clone.dequeue();
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                temp[i] = items[i];
            }
        }
        items = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
                new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
