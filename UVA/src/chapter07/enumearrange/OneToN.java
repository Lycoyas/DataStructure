package chapter07.enumearrange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**生成 1~n的排列
 * @author Lycoyas
 * @create 2022-10-02 09:26
 */
public class OneToN {

    public static void main(String[] args) {

        int n = 3;
        int[] perm = new int[n];
        printPermutation(n,perm,0);

    }

    static void printPermutation(int n,int[] perm,int cur) {

        if (cur == n) {
            for (int num : perm) {
                System.out.print(num+" ");
            }
            System.out.println();
        }else{
            for (int i = 1; i <= n; i++) {

                int ok=1;
                for (int j = 0; j < cur; j++) {
                    if(perm[j]==i) ok = 0;
                }
                if (ok==1) {
                    perm[cur] = i;
                    printPermutation(n, perm, cur + 1);
                }

            }

        }


    }

}
