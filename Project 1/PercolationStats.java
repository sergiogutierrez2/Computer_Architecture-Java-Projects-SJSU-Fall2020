import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    //...
    private double[] stats;
    private int T;

    // Perform T independent experiments (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        stats = new double[T];
        this.T = T;
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid values");
        }

        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation perc = new Percolation(N);
            int row;
            int col;
            while (!perc.percolates()) {
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    count++;
                }
            }
            stats[i] = count / (double) (N * N);
        }
    }

    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(stats);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return (mean() - ((1.96 * stddev()) / Math.sqrt(T)));
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return (mean() + ((1.96 * stddev()) / Math.sqrt(T)));
    }


    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
