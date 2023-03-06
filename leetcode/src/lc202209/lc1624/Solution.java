package lc202209.lc1624;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-17 11:49
 */
public class Solution {

    public static int maxLengthBetweenEqualCharacters(String s) {

        int max=-1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = s.lastIndexOf(ch);
            if (index != -1 && index > i) {
                max=Math.max(max, index - i - 1);
            }
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(maxLengthBetweenEqualCharacters("mgntdygtxrvxjnwksqhxuxtrv"));
    }

    public static int solution2(String s) {

        int firstIndex[] = new int[26];
        Arrays.fill(firstIndex, -1);

        int max=-1;

        for (int i = 0; i < s.length(); i++) {

            if (firstIndex[s.charAt(i) - 'a']==-1) {
                firstIndex[s.charAt(i) - 'a'] = i;
            }else{
                max=Math.max(max,i-firstIndex[s.charAt(i) - 'a']-1);
            }


        }
        return max;

    }
}
