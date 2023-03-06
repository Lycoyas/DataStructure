package chapter07.backtrack;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-04 16:27
 */
public class UVA524 {
    static class FastWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void println(Object object)  {
            try {
                writer.write(object.toString());
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void println()  {
            try {
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void close() {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
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
            return true;
        }


    }


    static boolean isPrime(int n) {
        if (n > 1) {

            int up = (int) Math.sqrt(n);
            for (int i = 2; i <= up; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    static int n;
    static int[] A = new int[n];
    static boolean[] isp = new boolean[33];
    static boolean[] vis = new boolean[n + 1];

    static FastReader fr = new FastReader();
    static FastWriter fw = new FastWriter();

    static int kase=0;
    public static void main(String[] args) {

        for (int i = 2; i <= 32; i++) {
            isp[i] = isPrime(i);
        }

        while (fr.hasNext()) {

            n = fr.nextInt();
            A = new int[n];
            vis = new boolean[n + 1];
            kase++;
            if (kase > 1) {
                fw.println();
            }
            fw.println("Case " + kase+":");
            A[0] = 1;
            vis[1] = true;

            dfs(1);

        }
        fw.close();

    }

    static void dfs(int cur) {

        if (cur == n ) {
            if (isp[A[0] + A[n - 1]]) {
                for (int i = 0; i < n; i++) {
                    if (i > 0) {
                        fw.print(" ");
                    }
                    fw.print(A[i]);
                }
                fw.println();
            }
        }else{
            for (int i = 2; i <= n; i++) {
                if (!vis[i] && isp[A[cur - 1] + i]) {
                    A[cur] = i;
                    vis[i] = true;
                    dfs(cur + 1);
                    vis[i] = false;
                }
            }
        }

    }


}


