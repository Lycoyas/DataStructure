package lc202209.lc547;

/**
 * @author Lycoyas
 * @create 2022-09-30 11:05
 */
public class Solution {

    static boolean[] visited;

    public int findCircleNum(int[][] isConnected) {
        visited = new boolean[isConnected.length];

        int ans=0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                ans++;
                dfs(isConnected, i);
            }
        }
        return ans;

    }

    void dfs(int[][] matrix, int u) {

        visited[u]=true;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[u][i] == 1 && !visited[i]) {
                dfs(matrix, i);
            }
        }

    }



}
