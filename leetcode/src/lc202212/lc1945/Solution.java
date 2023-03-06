package lc202212.lc1945;

/**
 * @author Lycoyas
 * @create 2022-12-14 12:28
 */
public class Solution {

    public int getLucky(String s, int k) {

        StringBuffer sb = new StringBuffer();

        for (char c : s.toCharArray()) {

            sb.append(""+(c-'a'+1));

        }

        int res=0;

        for (int i = 0; i < k; i++) {

            res = trans(sb.toString());
            sb = new StringBuffer(String.valueOf(res));

        }
        return res;

    }

    int trans(String str) {

        int sum=0;
        for (char c : str.toCharArray()) {
            sum+=c-'0';
        }
        return sum;
    }

}
