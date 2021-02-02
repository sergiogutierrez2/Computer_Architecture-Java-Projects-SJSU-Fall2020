import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

// A solver based on the A* algorithm for the 8-puzzle and its generalizations.
public class Solver {
    private SearchNode nodeSolver;
    private LinkedStack<Board> solution = new LinkedStack<Board>();
    private boolean isItSolvable;

    // Helper search node class.
    private class SearchNode {
        private Board board;
        private int moves;
        private SearchNode previous;

        SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        if (!initial.isSolvable()) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> pq = new MinPQ<>(new ManhattanOrder());
        pq.insert(new SearchNode(initial, 0, null));

        SearchNode searchNode = pq.delMin();
        SearchNode twinNode = new SearchNode(searchNode.board, searchNode.moves,
                                             searchNode.previous);

        MinPQ<SearchNode> secondPQ = new MinPQ<>(new HammingOrder());
        secondPQ.insert(twinNode);
        SearchNode second = secondPQ.delMin();

        while (!searchNode.board.isGoal() && !second.board.isGoal()) {
            for (Board neighbour : searchNode.board.neighbors()) {
                // solution.push(searchNode.board);
                if (searchNode.previous == null || !neighbour
                        .equals(searchNode.previous.board)) {
                    SearchNode search = new SearchNode(neighbour, 0, null);
                    search.moves = searchNode.moves + 1;
                    search.previous = searchNode;
                    pq.insert(search);
                    solution.push(searchNode.board);
                }
            }
            searchNode = pq.delMin();

            for (Board neighbour : second.board.neighbors()) {
                if (second.previous == null || !neighbour.equals(second.previous.board)) {
                    SearchNode search = new SearchNode(neighbour, second.moves + 1, second);
                    search.moves = second.moves + 1;
                    search.previous = second;
                    secondPQ.insert(search);
                    // solution.push(twin.board);
                }
            }
            second = secondPQ.delMin();
        }
        if (searchNode.board.isGoal()) {
            isItSolvable = true;
            nodeSolver = searchNode;
        }
    }


    // The minimum number of moves to solve the initial board.
    public int moves() {
        if (nodeSolver != null) {
            return nodeSolver.moves;
        }
        else return -1;
    }

    // Sequence of boards in a shortest solution.
    public Iterable<Board> solution() {
        if (isItSolvable) {
            Stack<Board> st = new Stack<>();
            for (SearchNode node = nodeSolver; node != null; node = node.previous) {
                st.push(node.board);
            }
            return st;
        }
        else {
            return null;
        }

        /* if (isItSolvable) {
            for (SearchNode node = nodeSolver; node != null; node = node.previous) {
                solution.push(node.board);
            }
            return solution;
        }
        else {
            return null;
        } */
    }

    // Helper hamming priority function comparator.
    private static class HammingOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            return a.board.hamming() + a.moves - (b.board.hamming() + b.moves);
        }
    }

    // Helper manhattan priority function comparator.
    private static class ManhattanOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            return a.board.manhattan() + a.moves - (b.board.manhattan() + b.moves);
        }
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
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
        else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}
