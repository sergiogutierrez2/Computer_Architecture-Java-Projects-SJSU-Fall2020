import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    private final Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException();
        }
        this.terms = terms;
        Arrays.sort(terms);
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        Term x = new Term(prefix, 0);

        int first = BinarySearchDeluxe.firstIndexOf(terms, x, Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(terms, x, Term.byPrefixOrder(prefix.length()));

        if (first == -1 || last == -1) {
            throw new NullPointerException();
        }
        
        Term[] allMatches = Arrays.copyOfRange(terms, first, last);
        Arrays.sort(allMatches, Term.byReverseWeightOrder());
        return allMatches;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }
        int numberMatches = BinarySearchDeluxe
                .lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length())) -
                BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0),
                                                Term.byPrefixOrder(prefix.length()));
        return numberMatches + 1;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
