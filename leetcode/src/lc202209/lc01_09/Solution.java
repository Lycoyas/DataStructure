package lc202209.lc01_09;

/**
 * @author Lycoyas
 * @create 2022-09-29 15:54
 */
public class Solution {

    static int N=200010, P = 13131;
    static int[] h=new int[N], p = new int[N];

    public boolean isFlipedString(String s1, String s2) {

        if(s1.length()!=s2.length()) return false;
        int n = s1.length();

        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] *P+ s2.charAt(i - 1);
        }

        int t = h[n];

        s1 = s1 + s1;

        p[0] = 1;
        for (int i = 1; i <= 2 * n; i++) {
            h[i] = h[i - 1] * P + s1.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }

        for (int i = 1; i + n - 1 <= 2 * n; i++) {

            int j=i+n-1,cur=h[j]-h[i-1]*p[n];
            if(cur==t) return true;

        }
        return false;
    }
}
