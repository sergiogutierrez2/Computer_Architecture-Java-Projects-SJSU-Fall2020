import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
// Note that each item from the sequence is printed out at most once.

// If running doesn't work as said on the PDF of the assignment, do
// $java Subset 8
// AA BB BB BB BB BB CC CC
// <ctrl-z>
// This worked for me, because <ctrl-d> messes the output.


public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        ResizingArrayRandomQueue<String> randomQueue = new ResizingArrayRandomQueue<String>();

        while (!StdIn.isEmpty()) {
            randomQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomQueue.dequeue());
        }
    }
}

