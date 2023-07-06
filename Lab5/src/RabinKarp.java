import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RabinKarp {
    private long patHash;       // pattern hash value
    private int M;              // pattern length
    private long Q;             // modulus
    private int R;              // radix
    private long RM;            // R^(M-1) % Q

    // constructor
    public RabinKarp(String pat) {
        M = pat.length();
        R = 256;
        Q = longRandomPrime();

        RM = 1;
        for (int i = 1; i <= M-1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);     // finds the hash value of pattern
    }

    // Computes the hash value of a string key of length M using the same polynomial hash function.
    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    // check for hash collision, using rolling hash function
    public List<Integer> search(String txt) {
        List<Integer> indices = new ArrayList<>();
        int N = txt.length();
        long txtHash = hash(txt, M);    // finds the hash value of text

        // If the hash value of the pattern matches the hash value of the first
        // M characters in txt, the method returns 0.
        if (patHash == txtHash) {
            indices.add(0);
        }

        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;   // rolling hash
            txtHash = (txtHash*R + txt.charAt(i)) % Q;              // Horner's rule

            // Las Vegas version, check for substring match is hash match ;
            // continue search if false collision
            if (patHash == txtHash) {
                indices.add(i - M + 1);
            }
        }
        return indices;
    }

    private static long longRandomPrime() {
        return 15485863L;
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