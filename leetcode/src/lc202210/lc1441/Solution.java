package lc202210.lc1441;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-10-15 14:50
 */
public class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> list = new ArrayList<>();
        int index=0;
        for (int i = 1; i <= n; i++) {
            if(index==target.length) break;
            if (target[index] == i) {
                list.add("Push");
                index++;
            }else{
                list.add("Push");
                list.add("Pop");
            }
        }
        return list;

    }
}
