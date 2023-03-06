package chapter06.uva1599;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Lycoyas
 * @create 2022-09-26 15:59
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


    static FastReader fr = new FastReader();



    static int n,m;

    static int[] dis;
    static boolean[] visited;

    static class Edge{
        int v, c;

        public Edge(int v, int c) {
            this.v=v;
            this.c = c;
        }
    }
    static List<Edge>[] G;

    static void addEdge(int u, int v, int c) {
        Edge edge = new Edge(v, c);
        G[u].add(edge);
    }

    public static void main(String[] args) {

        while(fr.hasNext()){
            n=fr.nextInt();
            m = fr.nextInt();

            dis = new int[n+1];
            visited = new boolean[n+1];
            G = new ArrayList[n+1];
            for (int i = 0; i < G.length; i++) G[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int u,v,c;
                u = fr.nextInt();
                v = fr.nextInt();
                c = fr.nextInt();
                addEdge(u, v, c);
                addEdge(v, u, c);
            }
            bfs();
            reBfs();
        }
    }

    //反向遍历
    static void bfs() {
        dis[n]=0;
        Deque<Integer> q = new ArrayDeque();
        q.offerLast(n);
        visited[n] = true;
        while (!q.isEmpty()) {
            int u=q.pollFirst();
            for(int i=0;i<G[u].size();i++){
                int v=G[u].get(i).v;
                if (!visited[v]) {
                    visited[v] = true;
                    dis[v]=dis[u]+1;
                    q.offerLast(v);
                }
            }
        }
        System.out.println(dis[1]);
    }

    //正向遍历
    static void reBfs() {
        Deque<Integer> q = new ArrayDeque();
        q.offerLast(1);
        visited = new boolean[n + 1];
        visited[1] = true;

        List<Integer> next = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        next.add(1);

        for (int i = 1; i <= dis[1]; i++) {

            int color = Integer.MAX_VALUE;
            for (int j = 0; j < next.size(); j++) {

                int u = next.get(j);
                for (int k = 0; k <G[u].size(); k++) {
                    int v = G[u].get(k).v;
                    int c=G[u].get(k).c;
                    if (dis[v]==dis[u]-1) {
                        color = Math.min(color, c);
                    }
                }

            }
            ans.add(color);
            List<Integer> next2 = new ArrayList<>();
            for (int j = 0; j < next.size(); j++) {

                int u = next.get(j);
                for (int k = 0; k <G[u].size(); k++) {
                    int v = G[u].get(k).v;
                    int c=G[u].get(k).c;
                    if (color==c&&dis[v]==dis[u]-1&&!visited[v]) {
                        visited[v] = true;
                        next2.add(v);
                    }
                }
            }
            next = next2;
        }
        System.out.print(ans.get(0));
        for (int i = 1; i < ans.size(); i++) {
            System.out.printf(" " + ans.get(i));
        }
        System.out.println();
    }

}
