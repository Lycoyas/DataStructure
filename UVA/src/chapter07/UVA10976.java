package chapter07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-29 21:36
 */
public class UVA10976 {
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

    static int[] X=new int[10000+5];
    static int[] Y=new int[10000+5];

    public static void main(String[] args) {

        int k;
        while (fr.hasNext()) {

            int ans=0;
            k = fr.nextInt();
            for (int y = k + 1; y <= 2 * k; y++) {

                if (y * k % (y - k) == 0) {
                    X[++ans] = y * k / (y - k);
                    Y[ans] = y;
                }

            }
            System.out.println(ans);
            for (int top = 1; top <= ans; top++) {

                System.out.printf("1/%d = 1/%d + 1/%d", k, X[top], Y[top]);
                System.out.println();
            }
        }


    }
}
