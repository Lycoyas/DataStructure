package chapter07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-29 21:04
 */
public class UVA11059 {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        boolean hasNext() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    String str = br.readLine();
                    if (str == null) {
                        return false;
                    }
                    st = new StringTokenizer(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.hasMoreElements();
        }
    }

    static FastReader fr = new FastReader();
    static int n;
    static int[] nums;
    static int kase = 0;

    public static void main(String[] args) {

        while (fr.hasNext()) {

            n = fr.nextInt();
            nums = new int[n];
            kase++;


            for (int i = 0; i < n; i++) {
                nums[i] = fr.nextInt();
            }
            solve();
            fr.nextLine();
            System.out.println();


        }

    }

    static void solve() {

        long multi = Long.MIN_VALUE;
        long sum = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum = 1;
                for (int k = i; k <= j; k++) {
                    sum *= nums[k];
                }
                if (sum > multi) {
                    multi = sum;
                }

            }
        }

        if (multi < 0) {
            System.out.println("Case #" + kase + ": The maximum product is " + "0" + ".");
        }else{
            System.out.println("Case #" + kase + ": The maximum product is " + multi + ".");
        }

    }
}
