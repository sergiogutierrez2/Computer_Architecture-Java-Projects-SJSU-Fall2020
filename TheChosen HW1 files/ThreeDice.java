// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class ThreeDice {
    public static void main(String[] args) {
        Random random = new Random();

        int roll1 = 1 + random.nextInt(6);
        int roll2 = 1 + random.nextInt(6);
        int roll3 = 1 + random.nextInt(6);

        int total = roll1 + roll2 + roll3;

        StdOut.println(total);
    }
}
