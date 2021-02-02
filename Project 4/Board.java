import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    private int[][] tiles;
    private int hamming;
    private int manhattan;
    private int N;

    // Construct a board from an N-by-N array of tiles, where
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank
    // square.
    public Board(int[][] tiles) {

        this.tiles = new int[tiles.length][tiles.length];
        N = tiles.length;
        this.hamming = 0;
        this.manhattan = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((this.tiles[i][j] != i * N + j + 1) && (this.tiles[i][j] != 0)) {
                    this.hamming++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != i * N + j + 1 && tiles[i][j] != 0) {
                    int x = (tiles[i][j] - 1) / N;
                    int y = (tiles[i][j] - 1) - (x * N);
                    this.manhattan += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }

    }

    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    // Size of this board.
    public int size() {
        return N * N;
    }

    // Number of tiles out of place.
    public int hamming() {
        return this.hamming;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {
        return this.manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return manhattan == 0;
    }

    // Is this board solvable?
    public boolean isSolvable() {
        if (((N % 2 != 0) && (inversions() % 2 != 0)) || (((blankPos() - 1) / N + inversions()) % 2
                == 0)) {
            return false;
        }

        return true;
    }

    // Does this board equal that?
    public boolean equals(Board that) {
        if (this.N != that.N) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {

        LinkedQueue<Board> queue = new LinkedQueue<Board>();
        int i = (blankPos() - 1) / N;
        int j = (blankPos() - 1) % N;
        int[][] neighbor;
        int temp;
        if (i - 1 >= 0) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i - 1][j];
            neighbor[i - 1][j] = temp;
            queue.enqueue(new Board(neighbor));
        }

        if (j + 1 < N) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i][j + 1];
            neighbor[i][j + 1] = temp;
            queue.enqueue(new Board(neighbor));
        }

        if (i + 1 < N) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i + 1][j];
            neighbor[i + 1][j] = temp;
            queue.enqueue(new Board(neighbor));
        }

        if (j - 1 >= 0) {
            neighbor = cloneTiles();
            temp = neighbor[i][j];
            neighbor[i][j] = neighbor[i][j - 1];
            neighbor[i][j - 1] = temp;
            queue.enqueue(new Board(neighbor));
        }
        return queue;
    }

    // String representation of this board.
    public String toString() {
        String s = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += String.format("%2d", tiles[i][j]);
                if (j < N - 1) {
                    s += " ";
                }
            }
            if (i < N - 1) {
                s += "\n";
            }
        }
        return s;
    }

    // Helper method that returns the position (in row-major order) of the
    // blank (zero) tile.
    private int blankPos() {
        int p = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                p++;
                if (tiles[i][j] == 0) {
                    return p;
                }
            }
        }
        return -1;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int firstTile = 0;
        int secondTile = 0;
        int counter = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int tile1 = tiles[i][j];
                firstTile++;

                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {

                        int tile2 = tiles[x][y];
                        secondTile++;

                        if (tile1 != 0 && tile2 != 0 && tile2 < tile1 && secondTile > firstTile) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

    // Helper method that clones the tiles[][] array in this board and
    // returns it.
    private int[][] cloneTiles() {
        int[][] t = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                t[i][j] = tiles[i][j];
        return t;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        StdOut.println(board.isSolvable());
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
        }
    }
}
