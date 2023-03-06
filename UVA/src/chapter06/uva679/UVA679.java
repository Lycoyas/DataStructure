package chapter06.uva679;


import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-19 11:31
 */
public class UVA679 {

    static class FastWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        public void print(Object object) throws IOException {
            writer.write(object.toString());
        }
        public void println(Object object) throws IOException {
            writer.write(object.toString());
            writer.write(System.lineSeparator());
        }
        @Override
        public void close() throws IOException {
            writer.close();
        }
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

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

    }

    public static void main(String[] args) {
        FastWriter fw = new FastWriter();
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        while (true) {

            int depth = fr.nextInt();
            if (depth == -1) {
                break;
            }
            int count = fr.nextInt();

            System.out.println(solution(depth, count));

        }



    }

    /**
     *
     * @param depth 深度
     * @param count 小球个数
     */
    static int solution(int depth, int count) {

        int k=1;

        for (int i = 1; i < depth; i++) {

            if (count % 2 == 0) {
                k=k*2+1;
                count = count / 2;
            }else{
                k=k*2;
                count = (count + 1) / 2;
            }

        }
        return k;

    }



}
