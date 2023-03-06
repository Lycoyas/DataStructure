package lc202210.lc940;

/**
 * @author Lycoyas
 * @create 2022-10-14 15:45
 */
public class Solution {

    public int distinctSubseqII(String s) {

        int mod = (int) 1e9 + 7;
        long result = 0L;
        long[] letter = new long[26];

        for (char sc : s.toCharArray()) {

            long pre = letter[sc - 'a'];
            letter[sc - 'a'] = (result + 1) % mod;
            result = (result + letter[sc - 'a'] - pre + mod) % mod;

        }
        return (int) result;

    }

}
