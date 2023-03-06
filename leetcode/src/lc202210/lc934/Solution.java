package lc202210.lc934;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Lycoyas
 * @create 2022-10-25 19:37
 */
public class Solution {

    public static void main(String[] args) {

        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        System.out.println(new Solution().shortestBridge(grid));

    }

    int[] dx = new int[]{0, 0, 1, -1};
    int[] dy = new int[]{1, -1, 0, 0};
    Deque<int[]> deque = new ArrayDeque<>();

    boolean isValid(int[][] grid, int m, int n) {
        return m >= 0 && n >= 0 && m < grid.length && n < grid[0].length;
    }

    void dfs(int[][] grid, int i, int j) {
        if(grid[i][j]==2) return;
        if (grid[i][j] == 0) {
            grid[i][j] = 2;
            deque.addLast(new int[]{i, j});
            return;
        }

        if (grid[i][j] == 1) {
            grid[i][j] = 2;

            for (int k = 0; k < 4; k++) {

                int nx = i+dx[k];
                int ny = j+dy[k];
                if (isValid(grid, nx, ny)) {
                    dfs(grid, nx, ny);
                }
            }

        }



    }

    public int shortestBridge(int[][] grid) {
        int result=0;

        boolean flag = false;
        for (int i = 0; i < grid.length; i++) {
            if(flag) break;
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    flag = true;
                    break;
                }

            }
        }

        while (!deque.isEmpty()) {
            result++;
            int n = deque.size();

            for (int i = 0; i < n; i++) {

                int[] d = deque.removeFirst();

                for (int k = 0; k < 4; k++) {

                    int nx = d[0]+dx[k];
                    int ny = d[1]+dy[k];
                    if (isValid(grid, nx, ny)) {

                        if (grid[nx][ny] == 0) {
                            grid[nx][ny]=2;
                            deque.addLast(new int[]{nx, ny});
                        } else if (grid[nx][ny] == 1) {
                            return result;
                        }


                    }
                }

            }


        }
        return result;


    }
}
