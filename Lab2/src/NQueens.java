import java.util.Arrays;
import java.util.Scanner;

public class NQueens {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the value of 'n' which is the number of matrix size: ");
        int n = input.nextInt();
        int[][] board = new int[n][n];

        if (ansNQueens(board, 0)) {
            System.out.println("Solution found:");
            for(int i = 0; i < n; ++i) {
                System.out.println(Arrays.toString(board[i]));
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    public static boolean ansNQueens(int[][] board, int col) {
        int n = board.length;
        if (col == n) {
            return true;
        } else {
            for(int row = 0; row < n; ++row) {
                if (isOk(board, row, col)) {
                    board[row][col] = 1;
                    if (ansNQueens(board, col + 1)) {
                        return true;
                    }
                    board[row][col] = 0;
                }
            }
            return false;
        }
    }
    public static boolean isOk(int[][] board, int row, int col) {
        int n = board.length;
        for(int i = 0; i < col; ++i) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        for(int i = row, j = col; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        for(int i = row, j = col; i < n && j >= 0; ++i, --j) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
