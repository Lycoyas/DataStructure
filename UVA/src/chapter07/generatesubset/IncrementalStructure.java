package chapter07.generatesubset;

import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-10-03 17:14
 */
public class IncrementalStructure {

    public static void main(String[] args) {
        printSubset(10,0);
        System.out.println("sum = " + sum);
    }

    static int[] A = new int[1000000];

    static int sum=0;
    static void printSubset(int n, int cur) {
        for (int i = 0; i < cur; i++) {
            System.out.print(A[i]+" ");
        }
        sum++;
        System.out.println();

        int s = cur > 0 ? A[cur-1]+1 : 0;

        for (int i = s; i < n; i++) {
            A[cur] = i;
            printSubset(n, cur + 1);
        }

    }

}
