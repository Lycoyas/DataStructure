package lc202211.lc764;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-11-09 11:46
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(1/0.0);
    }
    public int orderOfLargestPlusSign(int n, int[][] mines) {

        int[][] g = new int[n + 10][n + 10];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], 1);
        }
        for(int[] info:mines) g[info[0] + 1][info[1] + 1] = 0;
        int[][] a = new int[n + 10][n + 10];
        int[][] b = new int[n + 10][n + 10];
        int[][] c = new int[n + 10][n + 10];
        int[][] d = new int[n + 10][n + 10];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {
                if (g[i][j] == 1) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                }
                if (g[n + 1 - i][n + 1 - j] == 1) {
                    c[n + 1 - i][n + 1 - j] = c[n + 2 - i][n + 1 - j] + 1;
                    d[n + 1 - i][n + 1 - j] = d[n + 1 - i][n + 2 - j] + 1;
                }
            }

        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans = Math.max(ans, Math.min(Math.min(a[i][j], b[i][j]), Math.min(c[i][j], d[i][j])));
            }
        }
        return ans;

    }
}
