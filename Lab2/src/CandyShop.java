import java.util.Scanner;

public class CandyShop {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of candies present in the shop:");
        int n = input.nextInt(); // number of candies
        int[] values = new int[n]; // sentimental value of candies
        int[] weights = new int[n]; // weight of candies

        // read sentimental values
        System.out.println("Enter the sentimental value of each candy:");
        for (int i = 0; i < n; i++) {
            values[i] = input.nextInt();
        }
        // read weights
        System.out.println("Enter the weight of each candy:");
        for (int i = 0; i < n; i++) {
            weights[i] = input.nextInt();
        }
        System.out.println("Enter the maximum weight that can be carried by the bag:");
        int maxWeight = input.nextInt(); // maximum weight that can be carried

        int[][] mx = new int[n+1][maxWeight+1];

        // compute maximum sentimental value
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (weights[i-1] <= j) {
                    mx[i][j] = Math.max(mx[i-1][j], mx[i-1][j-weights[i-1]] + values[i-1]);
                } else {
                    mx[i][j] = mx[i-1][j];
                }
            }
        }
        System.out.println("The highest sentimental aggregated value is:");
        // output the highest sentimental aggregated value
        System.out.println(mx[n][maxWeight]);
    }
}
