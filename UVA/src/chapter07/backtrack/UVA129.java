package chapter07.backtrack;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-05 11:19
 */
public class UVA129 {

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


    public static void main(String[] args) {

        while (true) {
            cnt=0;
            S = new int[100];
            n = fr.nextInt();
            l = fr.nextInt();
            if(n==0&&l==0){
                break;
            }
            dfs(0);

        }
        fw.close();

    }

    static FastReader fr = new FastReader();
    static FastWriter fw = new FastWriter();
    static int cnt=0;
    static int n,l;
    static int[] S = new int[100];

    static void dfs(int cur) {
        if (cnt > n) {
            return;
        }
        if (cnt== n) {
            for (int i = 0; i < cur; i++) {
                if (i % 4 == 0 && i > 0) {
                    if(i%64==0) fw.println();
                    else fw.print(" ");
                }
                fw.print((char)('A'+S[i]));
            }
            fw.println();
            fw.println(cur);
            return;
        }

        for (int i = 0; i < l; i++) {
            S[cur] = i;
            int ok=1;
            for (int j = 1; j * 2 <= cur + 1; j++) {
                int equal=1;
                for (int k = 0; k < j; k++) {
                    if (S[cur - k] != S[cur - k - j]) {
                        equal=0;break;
                    }
                }
                if (equal == 1) {
                    ok=0;break;
                }
            }
            if (ok == 1) {
                cnt++;
                dfs(cur + 1);
            }
        }
    }


}
