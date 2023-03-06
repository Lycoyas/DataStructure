package lc202210.lc784;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-10-30 18:10
 */
public class Solution {
    public List<String> letterCasePermutation(String s) {

        return backtrack(s.toCharArray(), new ArrayList<>(), 0);

    }

    private List<String> backtrack(char[] sc, List<String> list,int start) {
        list.add(new String(sc));
        for (int i = start; i < sc.length; i++) {

            if(sc[i]<'A') continue;
            if (sc[i] >= 'a') {
                sc[i] = (char) (sc[i] - 32);
                backtrack(sc, list, i+1);
                sc[i] = (char) (sc[i] + 32);

            }else{
                sc[i] = (char) (sc[i] + 32); // 大写转小写
                backtrack(sc, list, i+1);
                sc[i] = (char) (sc[i] - 32); // 回溯

            }

        }
        return list;


    }

}
