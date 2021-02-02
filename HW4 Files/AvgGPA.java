// AvgGPA.java: Reads from standard input a list of letter grades and computes
// and prints the average GPA (the average of the numbers corresponding to
// the grades).

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AvgGPA {
    public static void main(String[] args) {
        // create symbol table of grades and values
        ArrayST<String, Double> st = new ArrayST<String, Double>();
        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);

        int total;
        double grandTotal = 0.0;

        for (total = 0; !StdIn.isEmpty(); total++) {
            String letter = StdIn.readString();
            double value = st.get(letter);
            grandTotal += value;
        }
        double gpa = grandTotal / total;
        StdOut.println("GPA = " + gpa);
    }
}
