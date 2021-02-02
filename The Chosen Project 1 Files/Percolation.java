import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {

    private boolean[][] grid;
    private int N;
    private int sitesOpened;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTop;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        this.grid = new boolean[N][N];
        this.N = N;
        this.sitesOpened = 0;
        this.top = 0;
        this.bottom = N * N + 1;
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.ufTop = new WeightedQuickUnionUF(N * N + 1);
        if (N <= 0) {
            throw new IllegalArgumentException("The Index is Out of Bounds");
        }

        for (int r = 0; r <= N; r++) {
            uf.union(encode(0, r), top);
            uf.union(encode(N - 1, r), bottom);
            ufTop.union(encode(0, r), top);
        }

    }

    // Open site (row, col) if it is not open already.
    public void open(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("The Index is Out of Bounds.");
        }

        if (!isOpen(i, j)) {
            grid[i][j] = true;
            sitesOpened++;

            if ((i + 1) < N && grid[i + 1][j]) {
                uf.union(encode(i, j), encode(i + 1, j));
                ufTop.union(encode(i, j), encode(i + 1, j));
            }
            if ((j + 1) < N && grid[i][j + 1]) {
                uf.union(encode(i, j), encode(i, j + 1));
                ufTop.union(encode(i, j), encode(i, j + 1));
            }
            if ((j - 1) >= 0 && grid[i][j - 1]) {
                uf.union(encode(i, j), encode(i, j - 1));
                ufTop.union(encode(i, j), encode(i, j - 1));
            }
            if ((i - 1) >= 0 && grid[i - 1][j]) {
                uf.union(encode(i, j), encode(i - 1, j));
                ufTop.union(encode(i, j), encode(i - 1, j));
            }
        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("The Index is Out of Bounds.");
        }
        if (grid[i][j]) {
            return true;
        }
        return false;
    }

    // Is site (row, col) full?
    public boolean isFull(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("The Index is Out of Bounds.");
        }
        if (grid[i][j] && ufTop.connected(encode(i, j), top)) {
            return true;
        }
        return false;
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return sitesOpened;
    }

    // Does the system percolate?
    public boolean percolates() {
        if (uf.connected(top, bottom)) {
            return true;
        }
        return false;
    }

    // An integer ID (1...N) for site (row, col).
    private int encode(int i, int j) {
        return (i * N + j + 1);
    }


    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        }
        else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
