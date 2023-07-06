import java.util.*;

public class BoyerMoore {

    /**
     * Searches for all occurrences of the given pattern in the given text using the Boyer-Moore algorithm.
     * @param text    the text to search in
     * @param pattern the pattern to search for
     * @return a list of indices where the pattern occurs in the text
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int[] last = buildLast(pattern); // build the last occurrence table
        int i = m - 1;
        int j = m - 1;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) { // if a match is found
                if (j == 0) { // if the entire pattern has been matched
                    indices.add(i); // add the index to the list of occurrences
                    i += m; // move to the next potential match in the text
                    j = m - 1; // reset the pattern index
                } else { // otherwise, continue matching
                    i--;
                    j--;
                }
            } else { // if a mismatch is not found
                int lo = last[text.charAt(i)]; // find the last occurrence of the mismatched character in the pattern
                i += m - Math.min(j, 1 + lo); // move to the next potential match in the text
                j = m - 1; // reset the pattern index
            }
        }
        return indices;
    }

    /**
     * Builds the last occurrence table for the given pattern.
     * @param pattern the pattern to build the table for
     * @return an array representing the last occurrence table
     */
    private static int[] buildLast(String pattern) {
        int[] last = new int[128]; // initialize an array to store the last occurrence of each character in the pattern
        int m = pattern.length();
        for (int i = 0; i < last.length; i++) {
            last[i] = -1; // initialize all values to -1
        }
        for (int i = 0; i < m; i++) {
            last[pattern.charAt(i)] = i; // update the last occurrence of each character in the pattern
        }
        return last; // return the last occurrence table
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the text: ");
            String text = scanner.nextLine();
            if (text.isEmpty()) {
                break;
            }

            System.out.print("Enter the pattern: ");
            String pattern = scanner.nextLine();

            RabinKarp rk = new RabinKarp(pattern);
            List<Integer> indices = rk.search(text);
            if (indices.isEmpty()) {
                System.out.println("Pattern not found");
            } else {
                System.out.print("Pattern " + pattern + " found at index");
                if (indices.size() > 1) {
                    System.out.print("es");
                }
                System.out.print(": ");
                for (int i = 0; i < indices.size(); i++) {
                    if (i > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(indices.get(i));
                }
                System.out.println();
            }
        }
    }

}
