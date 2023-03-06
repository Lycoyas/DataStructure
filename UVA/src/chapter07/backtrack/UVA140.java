package chapter07.backtrack;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-06 21:54
 */
public class UVA140 {
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

    static FastWriter fw = new FastWriter();
    static FastReader fr = new FastReader();

    static boolean G[][] = new boolean[30][30];
    static boolean id[] = new boolean[30];
    static int[] search = new int[30];
    static int tot;

    static boolean init() {

        String str = fr.nextLine();
        if ("#".equals(str)) {
            return false;
        }
        tot = 0;
        int pre=-1;
        G= new boolean[30][30];
        id= new boolean[30];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ':') {
                pre = str.charAt(i - 1) - 'A';
            }else{

                if (str.charAt(i) == ';') {
                    pre = -1;
                }
                if (pre != -1) {
                    G[pre][str.charAt(i) - 'A'] = true;
                    G[str.charAt(i) - 'A'][pre] = true;
                }

            }

            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                id[str.charAt(i) - 'A'] = true;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (id[i]) {
                search[tot++] = i;
            }
        }

        return true;

    }

    static int[] ans = new int[26];
    static int[] best = new int[26];
    static int best_dist;
    static boolean[] flag = new boolean[26];

    static void dfs(int step, int dist) {

        if (step == tot) {

            if (dist < best_dist) {
                for (int i = 0; i < tot; i++) {
                    best[i] = ans[i];
                }
                best_dist = dist;
            }

        }else{

            for (int i = 0; i < tot; i++) {
                if (!flag[search[i]]) {
                    boolean check_ok = true;
                    int cur_max_dist=0;
                    for (int j = 0; j < step; j++) {
                        if (G[ans[j]][search[i]]) {
                            if (step-j>=best_dist)
                            {
                                check_ok=false;//记录，表示下次不用往下寻找
                                break;//直接brak
                            }
                            if (step-j>cur_max_dist)
                                cur_max_dist=step-j;//更新一下这个点的dist
                        }
                    }
                    if (check_ok)
                    {
                        flag[search[i]]=true;
                        ans[step]=search[i];//记录ans数组
                        dfs(step+1,Math.max(dist,cur_max_dist));
                        flag[search[i]]=false;//重新标记为false，warning：如果不标记代表着永远不能再碰到这个点
                    }


                }
            }
        }

    }

    public static void main(String[] args) {

        while (fr.hasNext()) {
            if(!init()) break;

            best_dist=26;
            flag = new boolean[26];
            dfs(0, 0);
            for (int i = 0; i < tot; i++) {
                fw.print((char)(best[i]+'A')+" ");
            }
            fw.println("-> "+best_dist);

        }
        fw.close();

    }

}
