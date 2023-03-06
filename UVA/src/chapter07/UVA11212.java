package chapter07;


import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-18 19:16
 */
public class UVA11212 {
    static class FastReader{
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

        int nextInt(){return Integer.parseInt(next());}
        long nextLong(){return Long.parseLong(next());}
        double nextDouble(){return Double.parseDouble(next());}

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                }else{
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

    static FastReader fr = new FastReader();
    static FastWriter fw = new FastWriter();

    static int N = 12;
    static int[] a = new int[N];
    static int n, num;
    static int deepth;
    static boolean solve;

    static int check() {
        int different=0;
        for (int i = 1; i <= n; i++) {
            if (a[i] != a[i - 1] + 1) {
                different++;
            }
        }
        return different;
    }

    public static void main(String[] args) {
        while (true) {

            n = fr.nextInt();
            if (n == 0) {
                break;
            }
            solve = false;
            a = new int[N];
            for (int i = 1; i <= n; i++) {
                a[i] = fr.nextInt();
            }

            for (deepth = 0; deepth <= n; deepth++) {
                IDDFS(0);
                if(solve) break;
            }

            fw.println("Case " + ++num + ": " + deepth);
        }
        fw.close();
    }

    static void IDDFS(int cur) {

        int ret = check();
        if(cur*3+ret>deepth*3) return;
        if (ret == 0) {
            solve = true;
            return;
        }

        int[] copy_first = new int[N + 2];
        int[] copy_second = new int[N + 2];
        System.arraycopy(a, 0, copy_first, 0, a.length);
        for (int i = 1; i <= n; i++) {
            if (a[i] != a[i - 1] + 1) {
                for (int j = i; i <= n; j++) {
                    if(j<n&&a[j+1]==a[j]+1) continue;
                    if(a[j+1]>a[j]) continue;
                    for (int k = i; k <= j; k++) copy_second[k] = a[k];
                    for (int k = j + 1; k <= n; k++) {
                        if(k<n&&a[k+1]==a[k]+1) continue;
                        for (int left = i, right = j + 1; right <= k; left++, right++) {
                            a[left] = a[right];
                        }
                        for (int right = i + k - j, left = i; left <= j; left++, right++) {
                            a[right] = copy_second[left];
                        }
                        IDDFS(cur + 1);
                        if(solve) return;
                        System.arraycopy(copy_first, 0, a, 0, a.length);

                    }

                }

            }

        }


    }



}
