package dscourse.arraylinklist;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Lycoyas
 * @create 2023-02-22 21:43
 */
public class LC373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<int[]> pq=new PriorityQueue<>(
                (a,b)->{
                    return (a[0]+a[1])-(b[0]+b[1]);
                }
        );

        for (int i = 0; i < nums1.length; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }

        List<List<Integer>> list = new ArrayList<>();

        while (!pq.isEmpty() && k > 0) {

            int[] temp = pq.poll();
            List<Integer> kv = new ArrayList<>();
            kv.add(temp[0]);
            kv.add(temp[1]);
            kv.add(temp[2]);
            k--;

            if(temp[2]+1<nums2.length){
                pq.add(new int[]{temp[0], nums2[temp[2] + 1], temp[2] + 1});
            }

            list.add(kv);

        }

        return list;

    }


}
