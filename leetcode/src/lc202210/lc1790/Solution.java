package lc202210.lc1790;

/**
 * @author Lycoyas
 * @create 2022-10-11 09:11
 */
public class Solution {
    public boolean areAlmostEqual(String s1, String s2) {

        int index = 0;


        while (index < s1.length() && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }
        if (index == s1.length()) {
            return true;
        } else {
            int idx2 = s2.indexOf(s1.charAt(index), index + 1);
            for (int k = idx2; k != -1; k = s2.indexOf(s1.charAt(index), k + 1)) {
                char[] ch1 = s1.toCharArray();
                char[] ch2 = s2.toCharArray();
                char temp = ch2[index];
                ch2[index] = ch1[index];
                ch2[k] = temp;
                boolean flag = true;
                for (int i = index + 1; i < s1.length(); i++) {

                    if (ch1[i] != ch2[i]) {
                        flag = false;
                        break;
                    }

                }
                if (flag) {
                    return true;
                }
            }
            return false;
        }


    }
}
