package dscourse.arraylinklist;

import java.util.PriorityQueue;

/**
 * @author Lycoyas
 * @create 2023-02-22 20:05
 */
public class LC378 {

    public int kthSmallest(int[][] matrix, int k) {

        PriorityQueue<int []> pq=new PriorityQueue<>((a,b)->{
            return a[0] - b[0];
        });

        for (int i = 0; i < matrix.length; i++) {
            pq.add(new int[]{matrix[i][0], i, 0});
        }

        int res=0;
        while(!pq.isEmpty()&&k>0){

            int[] temp=pq.poll();
            res=temp[0];
            k--;

            int i=temp[1],j=temp[2];

            if(j+1<matrix[0].length){

                pq.add(new int[]{matrix[i][j+1],i,j+1});

            }

        }

        return res;


    }

}
