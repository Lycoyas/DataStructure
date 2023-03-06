package chapter07._05pathsearch;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-09 16:22
 */
public class EightNum1 {
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

    static FastReader fr = new FastReader();

    static class State {
        int[] graph = new int[9];
    }

    static int maxState = 1000000;
    static State[] st = new State[maxState];
    static State goal = new State();

    static int[] dist = new int[maxState];
    static int[] fa = new int[maxState];

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {

        st[1] = new State();
        st[1].graph = new int[]{2, 6, 4, 1, 3, 7, 0, 5, 8};
        goal.graph = new int[]{8, 1, 5, 7, 3, 6, 4, 0, 2};

        int ans = bfs();
        if (ans > 0) {
            System.out.println(dist[ans]);
        }else{
            System.out.println(-1);
        }


    }



    static boolean memcmp(State s, State goal) {

        for (int i = 0; i < 9; i++) {
            if (s.graph[i] != goal.graph[i]) {
                return false;
            }
        }
        return true;

    }

    static int bfs() {
        init_lookup_table();
        int front = 1, rear = 2;

        while (front < rear) {
            State s = st[front];
            if (memcmp(s, goal)) {//达到目标状态
                return front;
            }
            int z;
            for (z = 0; z < 9; z++) {
                if(s.graph[z]==0)break;//找0的位置
            }
            //获取行列编号
            int x = z / 3, y = z % 3;
            for (int d = 0; d < 4; d++) {
                int newx = x + dx[d];
                int newy = y + dy[d];
                int newz = newx * 3 + newy;
                if (newx >= 0 && newx < 3 && newy >= 0 && newy < 3) {
                    st[rear] = new State();
                    State t = st[rear];
                    t.graph = Arrays.copyOf(s.graph, 9);
                    t.graph[newz] = s.graph[z];
                    t.graph[z] = s.graph[newz];
                    dist[rear]=dist[front]+1;
                    if(try_to_insert(rear)) rear++;
                }
            }
            front++;
        }
        return 0;
    }


    static Set<Integer> set = new HashSet<>();

    static void init_lookup_table() {
        set.clear();
    }

    static boolean try_to_insert(int s) {
        int v=0;
        for (int i = 0; i < 9; i++) {
            v = v * 10 + st[s].graph[i];
        }
        if (set.contains(v)) {
            return false;
        }
        set.add(v);
        return true;
    }

}
