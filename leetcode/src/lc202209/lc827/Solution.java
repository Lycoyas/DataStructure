package lc202209.lc827;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lycoyas
 * @create 2022-09-18 19:52
 */
public class Solution {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1,1,0,0},{0,0,1,1,0},{1,1,0,1,1},{1,1,0,1,0},{0,1,0,0,1}};
        new Solution().largestIsland(grid);

    }
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int result=0,index=2;

        HashMap<Integer, Integer> areasMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    areasMap.put(index, calculateAreas(index++, grid, i, j));
                }
            }
        }

        if(areasMap.size()==0) return 1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> islands = getIslands(grid, i, j);
                    if(islands.size()==0) continue;
                    result = Math.max(result, islands.stream().map(item -> areasMap.get(item)).reduce(Integer::sum).get()+1);
                }
            }
        }

        if(result==0) return areasMap.get(2);

        return result;



    }

    public int calculateAreas(int index, int[][] grid, int row, int column) {

        if(!isLegal(grid,row,column)||grid[row][column]!=1) return 0;
        grid[row][column] = index;
        return 1+calculateAreas(index, grid, row + 1, column) + calculateAreas(index, grid, row - 1, column) + calculateAreas(index, grid, row, column + 1) + calculateAreas(index, grid, row, column - 1);
    }






    public boolean isLegal(int[][] grid, int row, int column) {

        return row >= 0 && row < grid.length && column >= 0 && column < grid[0].length;

    }

    public Set<Integer> getIslands(int[][] grid, int row, int column) {

        Set<Integer> islands = new HashSet<>();
        if (isLegal(grid, row + 1, column)&&grid[row+1][column]!=0) {
            islands.add(grid[row + 1][column]);
        }
        if (isLegal(grid, row - 1, column)&&grid[row-1][column]!=0) {
            islands.add(grid[row - 1][column]);
        }
        if (isLegal(grid, row , column+1)&&grid[row][column+1]!=0) {
            islands.add(grid[row ][column+1]);
        }
        if (isLegal(grid, row , column-1)&&grid[row][column-1]!=0) {
            islands.add(grid[row ][column-1]);
        }
        return islands;

    }
}
