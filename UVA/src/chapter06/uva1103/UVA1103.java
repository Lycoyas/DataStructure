package chapter06.uva1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Lycoyas
 * @create 2022-09-23 11:06
 */
public class UVA1103 {

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
    static char code[] = {'W', 'A', 'K', 'J', 'S', 'D'};
    static HashMap<Character,String> hex = new HashMap();
    static{
        hex.put('0', "0000");
        hex.put('1',"0001");
        hex.put('2',"0010");
        hex.put('3',"0011");
        hex.put('4',"0100");
        hex.put('5',"0101");
        hex.put('6',"0110");
        hex.put('7',"0111");
        hex.put('8',"1000");
        hex.put('9',"1001");
        hex.put('a',"1010");
        hex.put('b',"1011");
        hex.put('c',"1100");
        hex.put('d',"1101");
        hex.put('e',"1110");
        hex.put('f',"1111");
    }

    static int[][] dir = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static void main(String[] args) {

        int kase=0;

        while (fr.hasNext()) {

            int H = fr.nextInt();
            int W = fr.nextInt();
            if (H == 0 && W == 0) {
                break;
            }
            StringBuilder result = new StringBuilder();
            char[][] map = input(H, W*4);

            int[] ans = new int[1];
            dfs(map, 0, 0, '0', ans);

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == '1') {
                        ans[0]=0;
                        dfs(map,i, j, '1', ans);
                        result.append(code[ans[0]]);
                    }
                }
            }

            char[] chars = result.toString().toCharArray();
            Arrays.sort(chars);
            System.out.printf("Case %d: %s\n", ++kase, new String(chars));

        }


    }

    static char [][] input(int H,int W) {
        char[][] map = new char[H + 2][W + 2];
        List<StringBuilder> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(W+2);

        for (int i = 0; i < W+2; i++) {
            sb.append("0");
        }
        list.add(sb);

        for (int i = 0; i < H; i++) {
            StringBuilder temp = new StringBuilder(W+2);
            temp.append("0");
            list.add(temp);
        }

        sb = new StringBuilder(W+2);

        for (int i = 0; i < W+2; i++) {
            sb.append("0");
        }
        list.add(sb);

        for (int i = 1; i <= H; i++) {
            StringBuilder temp = list.get(i);

            char[] s = fr.nextLine().toCharArray();

            for (char ch : s) {
                temp.append(hex.get(ch));
            }
            temp.append("0");

        }

        for (int i = 0; i < map.length; i++) {
            map[i] = list.get(i).toString().toCharArray();
        }

        return map;
    }

    static void dfs(char[][]map, int row, int column, char ch, int[] ans) {

        map[row][column] = (char) (ch + 2);
        for (int k = 0; k < 4; k++) {

            int nr = row + dir[k][0];
            int nc = column + dir[k][1];
            if (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length) {
                if (ch == '1' && map[nr][nc] == '0') {
                    ans[0]++;
                    dfs(map,nr, nc, '0', ans);
                }
                if (ch == map[nr][nc]) {
                    dfs(map,nr, nc, ch, ans);
                }
            }
        }

    }


}
