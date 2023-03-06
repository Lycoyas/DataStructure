package chapter06.uva572;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-22 19:30
 */
public class UVA572 {

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt(){return Integer.parseInt(next());}


        String nextLine() {
            String str = "";
            if (st.hasMoreTokens()) {
                str = st.nextToken("\n");
            }else{
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return str;
        }

        boolean hasNext() {
            if (st == null || !st.hasMoreTokens()) {
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

    static int count;
    public static void main(String[] args) {

        FastReader fr = new FastReader();

        while (true) {
            int m = fr.nextInt();
            int n = fr.nextInt();
            if (m == 0 && n == 0) {
                break;
            }

            char[][] oil = new char[m][n];
            int[][] visited = new int[m][n];
            for (int i = 0; i < m; i++) {
                String str = fr.nextLine();
                oil[i] = str.toCharArray();
            }

            int count=0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (oil[i][j] == '@' && visited[i][j] == 0) {
                        dfs(oil, visited, i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);

        }




    }

    static void dfs(char[][] oil, int[][] visited, int i, int j) {

        if (visited[i][j] == 1) {
            return;
        }
        if (oil[i][j] == '*') {
            return;
        }

        visited[i][j] = 1;
        if (isValid(oil, i + 1, j)) {
            dfs(oil, visited, i +1, j);
        }
        if (isValid(oil, i - 1, j)) {
            dfs(oil, visited, i -1, j);
        }
        if (isValid(oil, i , j+1)) {
            dfs(oil, visited, i , j+1);
        }
        if (isValid(oil, i , j-1)) {
            dfs(oil, visited, i, j-1);
        }
        if (isValid(oil, i+1 , j+1)) {
            dfs(oil, visited, i+1, j+1);
        }
        if (isValid(oil, i -1, j-1)) {
            dfs(oil, visited, i-1, j-1);
        }
        if (isValid(oil, i +1, j-1)) {
            dfs(oil, visited, i+1, j-1);
        }
        if (isValid(oil, i-1 , j+1)) {
            dfs(oil, visited, i-1, j+1);
        }


    }

    static boolean isValid(char[][] oil, int i, int j) {
        if (i < 0 || j < 0 || i >= oil.length || j >= oil[0].length) {
            return false;
        }
        return true;
    }

}
