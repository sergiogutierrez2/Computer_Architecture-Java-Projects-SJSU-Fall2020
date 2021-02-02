// ramanujan.java: Prints the integers <= N (command-line argument) that can be
// expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Ramanujan {
    // A data type that encapsulates a pair of numbers (i, j)
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }
    }

    public static void main(String[] args) {

        int number = Integer.parseInt(args[0]);
        int n = 1000;
        int count;
        MinPQ<Pair> minQ = new MinPQ<Pair>();

        int counter = 1;
        while (counter <= n) {
            minQ.insert(new Pair(counter, counter));
            counter++;
        }

        Pair p = new Pair(0, 0);

        while (!minQ.isEmpty()) {
            count = 1;
            Pair value = minQ.delMin();

            //  Pair p = minQ.min();

            if (p.sumOfCubes == value.sumOfCubes) {
                count++;
                if (count == 2) {
                    StdOut.println(
                            p.sumOfCubes + " = " + p.i + "^3 + " + p.j + "^3" + " = " + value.i
                                    + "^3 + " + value.j + "^3");
                }
            }
            if (value.sumOfCubes > number) {
                break;
            }

            p = value;
            if (value.j < n) {
                minQ.insert(new Pair(value.i, value.j + 1));
            }
        }
    }
}


