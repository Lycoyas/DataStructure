package lc202211.lc1704;

/**
 * @author Lycoyas
 * @create 2022-11-11 10:44
 */
public class Solution {
    public boolean halvesAreAlike(String s) {

        String s1 = s.substring(0, s.length() / 2);
        String s2 = s.substring(s.length() / 2);

        int cnt1=0,cnt2=0;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == 'a' || c1 == 'e' || c1 == 'i' || c1 == 'o' || c1 == 'u'
                    || c1 == 'A' || c1 == 'E' || c1 == 'I' || c1 == 'O' || c1 == 'U') {
                cnt1++;
            }
            if (c2 == 'a' || c2 == 'e' || c2 == 'i' || c2 == 'o' || c2 == 'u'
                    || c2 == 'A' || c2 == 'E' || c2 == 'I' || c2 == 'O' || c2 == 'U') {
                cnt2++;
            }

        }
        return cnt1 == cnt2;

    }
}
