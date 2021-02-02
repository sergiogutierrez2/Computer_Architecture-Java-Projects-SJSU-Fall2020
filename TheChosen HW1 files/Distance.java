// Distance.java: reads two position vectors from standard input, and then
// computes and writes the Manhattan distance between the two.

import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdOut;

public class Distance {
    // Returns the Euclidean distance between the position vectors x and y.
    private static double distance(double[] x, double[] y) {

        double total = 0.0;
        double counter = 0.0;
        int i = 0;
        if (x.length == y.length) {
            for (i = 0; i < x.length; i++) {
                counter = x[i] - y[i];
                if (counter < 0) {
                    total = total + (counter * (-1));
                }
                else
                    total = total + counter;
            }
            return total;
        }
        else {
            StdOut.println("X and Y vector arrays must have same number of elements.");
            return 0;
        }
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[] x = StdArrayIO.readDouble1D();
        double[] y = StdArrayIO.readDouble1D();
        StdOut.println(distance(x, y));
    }
}
