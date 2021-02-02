// Im not the best at this

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class Spell {
    public static void main(String[] args) {

        SeparateChainingHashST<String, String> st = new SeparateChainingHashST<String, String>();

        In inputOne = new In(args[0]);
        while (inputOne.hasNextLine()) {
            String grammar = inputOne.readString();
            String correct = inputOne.readLine().trim();
            st.put(grammar, correct);
        }

        int counter = 0;

        StdOut.println("");

        In inputTwo = new In(args[1]);
        while (inputTwo.hasNextLine()) {
            counter++;
            String line = inputTwo.readLine().trim();
            String[] words = line.split((",| "));
            for (int i = 0; i < words.length; i++) {
                if (st.contains(words[i])) {
                    StdOut.println(
                            st.get(words[i]) + ":" + counter + " -> " + st.get(words[i + 1]));
                }
            }
        }

    }
}
