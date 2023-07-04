import java.util.*;

public class LIS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the sequence of numbers: ");
        String[] input = sc.nextLine().split(" ");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
        int[] lis = findLIS(nums);
        System.out.println("Length of LIS: " + lis.length);
        System.out.println("LIS: " + Arrays.toString(lis));
    }

    public static int[] findLIS(int[] nums) {
        int n = nums.length;
        int[] mx = new int[n];
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        int maxLength = 1;
        int endIndex = 0;
        for (int i = 0; i < n; i++) {
            mx[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && mx[j] + 1 > mx[i]) {
                    mx[i] = mx[j] + 1;
                    prev[i] = j;
                }
            }
            if (mx[i] > maxLength) {
                maxLength = mx[i];
                endIndex = i;
            }
        }
        int[] lis = new int[maxLength];
        int index = maxLength - 1;
        while (endIndex != -1) {
            lis[index--] = nums[endIndex];
            endIndex = prev[endIndex];
        }
        return lis;
    }
}
