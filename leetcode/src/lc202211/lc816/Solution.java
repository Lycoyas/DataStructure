package lc202211.lc816;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-11-07 10:52
 */
public class Solution {
    public List<String> ambiguousCoordinates(String s) {
        List<String> result = new ArrayList<>();
        s = s.substring(1, s.length() - 1);
        for (int i = 1; i < s.length(); i++) {

            for (String x : genNums(s.substring(0, i))) {

                for (String y : genNums(s.substring(i))) {

                    result.add(new StringBuilder("(").append(x).append(", ").append(y).append(")").toString());

                }

            }

        }
        return result;
    }

    private List<String> genNums(String num) {

        List<String> list = new ArrayList<>();
        int i = 1;
        String left, right;
        while (i <= num.length()) {
            left = num.substring(0, i);
            right = num.substring(i++);
            if((!left.equals("0")&&left.charAt(0)=='0')||
                    (!right.isEmpty()&&right.charAt(right.length()-1)=='0')) continue;
            if(right.isEmpty()) list.add(left);
            else list.add(new StringBuilder(left).append(".").append(right).toString());
        }
        return list;

    }
}


