package fastio;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-19 20:05
 */
public class Main {
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

    public static void main(String[] args)
    {
        FastReader fr = new FastReader();
        FastWriter fw = new FastWriter();

        int num=fr.nextInt();
        if (num == 0) {
            return;
        }
        while (true) {

            fw.print(num);
            num = fr.nextInt();
            if (num == 0) {
                break;
            }else{
                fw.print(" ");
            }

        }

    }
}
