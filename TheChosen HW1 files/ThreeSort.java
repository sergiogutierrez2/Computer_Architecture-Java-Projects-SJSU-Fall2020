// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int middle;

        int mi = Math.min(a, b);
        mi = Math.min(mi, c);

        int ma = Math.max(a, b);
        ma = Math.max(ma, c);

        if ((a == b || a == c) || a > mi && a < ma) {
            middle = a;
        }
        else if ((b == c) || b > mi && b < ma) {
            middle = b;
        }
        else {
            middle = c;
        }

        StdOut.println(mi + " " + middle + " " + ma);
    }
}
