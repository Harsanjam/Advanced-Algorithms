import java.util.Scanner;

public class RodCutting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Reading user input for the length of the rod
        System.out.println("What is the length of the rod?");
        int n = scanner.nextInt();

        // Creating an array to store the prices of each element
        int[] prices = new int[n];

        // Reading user input for the prices of each element
        System.out.println("What is the price of the elements?");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        // Calling the maxRevenue function to calculate the maximum value obtainable by cutting up the rod and selling the pieces
        int maxRev = maxRevenue(n, prices);
        System.out.println("The maximum value obtainable by cutting up the rod and selling the pieces: "+maxRev);
    }

    // Function to calculate the maximum value obtainable by cutting up the rod and selling the pieces
    public static int maxRevenue(int n, int[] prices) {
        // Initializing an array to store the maximum revenue for each length of the rod
        int[] r = new int[n+1];
        // Base case
        r[0] = 0;
        // Looping through all possible lengths of the rod
        for (int i = 1; i <= n; i++) {
            // Initializing the maximum revenue to a very small value
            int maxRevenue = Integer.MIN_VALUE;
            // Looping through all possible cuts for the current length of the rod
            for (int j = 1; j <= i; j++) {
                // Calculating the revenue for the current cut and updating the maximum revenue if necessary
                maxRevenue = Math.max(maxRevenue, prices[j-1] + r[i-j]);
            }
            // Storing the maximum revenue for the current length of the rod in the array
            r[i] = maxRevenue;
        }
        // Returning the maximum revenue for the original length of the rod
        return r[n];
    }

}
