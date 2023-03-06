package lc202210.lc886;

/**
 * @author Lycoyas
 * @create 2022-10-16 15:58
 */
public class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {

        int[][] matrix = new int[n + 1][n + 1];
        for (int[] item : dislikes) {
            matrix[item[0]][item[1]] = matrix[item[1]][item[0]] = 1;
        }

        int[] record = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if(record[i]==0 && !dfs(matrix,record,i,1,n)) return false;
        }
        return true;

    }

    public boolean dfs(int[][] matrix, int[] record, int index, int group, int n) {

        record[index]=group;
        for (int i = 1; i <= n; i++) {
            if(i==index) continue;
            if(matrix[index][i]==1&&record[i]==group) return false;
            if(matrix[index][i]==1&&record[i]==0&&!dfs(matrix,record,i,group*-1,n)) return false;
        }
        return true;

    }
}
