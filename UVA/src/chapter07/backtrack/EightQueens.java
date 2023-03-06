package chapter07.backtrack;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-10-04 11:37
 */
public class EightQueens {

    static int sum=0;
    static int n=8;
    static boolean[][] vis = new boolean[3][n * 2 ];
    static int[] C = new int[n];
    public static void search(int cur) {
        if(cur==n){
            sum++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!vis[0][i] && !vis[1][cur + i] && !vis[2][i - cur + n]) {
                C[cur]=i;
                vis[0][i]=vis[1][cur + i]=vis[2][i - cur + n]=true;
                search(cur + 1);
                vis[0][i]=vis[1][cur + i]=vis[2][i - cur + n]=false;
            }
        }


    }

    public static void search1(int cur) {
        if(cur==n){
            sum++;
            System.out.println(Arrays.toString(C));
            return;
        }
        for (int i = 0; i < n; i++) {
            int ok=1;
            C[cur] = i;
            for (int j = 0; j < cur; j++) {
                if (C[cur] == C[j] || C[cur] - cur == C[j] - j || C[cur] + cur == C[j] + j) {
                    ok=0;
                    break;
                }
            }
            if (ok == 1) {
                search1(cur + 1);
            }
        }
    }

    public static void main(String[] args) {
        search1(0);
        System.out.println("sum = " + sum);
    }

}
