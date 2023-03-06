package lc202211.lc1668;

/**
 * @author Lycoyas
 * @create 2022-11-03 16:19
 */
public class Solution2 {
    public int maxRepeating(String sequence, String word) {

        int n = sequence.length(), m = word.length(),ans=0;
        int[] f = new int[n];
        for (int i = 0; i <= n; i++) {
            if(i-m<0) continue;
            if(sequence.substring(i-m,i).equals(word)) f[i] = f[i - m] + 1;
            ans = Math.max(ans, f[i]);
        }
        return ans;

    }
}
