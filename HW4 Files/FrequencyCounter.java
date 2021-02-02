import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {

        // NOTE, as per the instructions,
        // Some of the code is from the book
        int diff = 0, words = 0;
        int length = Integer.parseInt(args[0]);
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < length) {
                continue;
            }
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                diff++;
            }
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        int counter = 1;
        for (String s : st.keys()) {
            if (s.equals(max)) {
                counter = counter + 1;
            }
        }
        // StdOut.println(counter);
        // StdOut.println(max);

        for (String s : st.keys()) {
            if (st.get(s) == counter) {
                StdOut.print(s + " ");
            }
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + diff);
        StdOut.println("words = " + words);
    }
}


