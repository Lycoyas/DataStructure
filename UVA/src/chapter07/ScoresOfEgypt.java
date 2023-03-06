package chapter07;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-10-17 20:55
 */
public class ScoresOfEgypt {

    static int getFirst(int a, int b) {
        for (int i = 1;i<=b; i++) {
            if (i * a >= b) {
                return i;
            }
        }
        return -1;
    }

    static int[] ans=new int[20];
    static int[] v=new int[20];
    static int maxd;


    public static void main(String[] args) {
        int a = 495, b = 499;

        int ok=0;
        for (maxd = 1; ; maxd++) {
            ans = new int[20];
            Arrays.fill(ans, -1);
            if (dfs(0, getFirst(a, b), a, b)) {
                ok=1;
                break;
            }

        }

        System.out.println(Arrays.toString(ans));


    }

    //如果当前解v比目前最优解ans更优，更新ans
    static boolean better(int d) {
        for (int i = d; i >= 0; i--) {
            if (v[i] != ans[i]) {
                return ans[i] == -1 || v[i] < ans[i];
            }
        }
        return false;
    }

    static boolean dfs(int d, int from, int aa, int bb) {
        if (d == maxd) {
            if(bb%aa!=0) return false;
            v[d] = bb / aa;
            System.out.println(Arrays.toString(v));
            if (better(d)) {
                System.arraycopy(v, 0, ans, 0, d+1);
            }
            return true;
        }

        boolean ok=false;
        from = Math.max(from, getFirst(aa, bb));

        for (int i = from ; ; i++){
            if(bb*(maxd+1-d)<=i*aa)break;
            v[d] = i;
            int b2=bb*i;
            int a2 = aa * i - bb;
            int g = gcd(a2, b2);//求最大公因数约分
            if (dfs(d + 1, i + 1, a2 / g, b2 / g)) {
                ok = true;
            }
        }
        return ok;
    }



    static int gcd(int m, int n) {
        for (int i=((m<=n)?m:n);i>=1;i--){
            if (m%i==0&&n%i==0){
                return i;
            }
        }

        return -1;

    }

}
