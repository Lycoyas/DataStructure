package lc202209.lc01_08;

import java.util.HashSet;

/**
 * @author Lycoyas
 * @create 2022-09-30 10:31
 */
public class Solution {

    public static void main(String[] args) {

        int[][] matrix={
                {1,1,1},
                {1,0,1},
        {1,1,1}
    };

        new Solution().setZeroes(matrix);

    }

    public void setZeroes(int[][] matrix) {

        HashSet<Integer> setRow = new HashSet<>();
        HashSet<Integer> setCol = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == 0) {
                    setRow.add(i);
                    setCol.add(j);
                }

            }
        }

        for (Integer row : setRow) {

            for (int j = 0; j < matrix[0].length; j++) {
                matrix[row][j] = 0;
            }

        }
        for (Integer col : setCol) {

            for (int j = 0; j < matrix.length; j++) {
                matrix[j][col] = 0;
            }

        }

        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(" [");
            for (int j = 0; j < matrix[i].length; j++) {
                if(j>0) System.out.print(",");
                System.out.print(matrix[i][j]);
            }
            if(i<matrix.length-1) System.out.println("],");
            else System.out.println("]");
        }
        System.out.println("]");



    }

}
