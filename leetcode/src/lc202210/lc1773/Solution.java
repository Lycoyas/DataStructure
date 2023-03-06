package lc202210.lc1773;

import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-10-29 19:07
 */
public class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {

        int key = -1;
        switch (ruleKey) {
            case "type":key=0;break;
            case "color":key=1;break;
            case "name":key=2;break;
        }
        int result=0;
        for (List<String> item : items) {

            if (item.get(key).equals(ruleValue)) {
                result++;
            }

        }
        return result;

    }
}
