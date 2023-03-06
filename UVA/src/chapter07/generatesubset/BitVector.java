package chapter07.generatesubset;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author Lycoyas
 * @create 2022-10-03 17:31
 */
public class BitVector {
    public static void main(String[] args) {
        printSubset(10,0);
        System.out.println("sum = " + sum);
    }

    static int sum=0;
    static int[] B = new int[1000000];
    static void printSubset(int n, int cur) {
        if (cur == n) {
            sum++;
            for (int i = 0; i < cur; i++) {
                if(B[i]==1) System.out.print(i);
            }
            System.out.println();
            return;
        }
        B[cur]=1;
        printSubset(n, cur + 1);
        B[cur] = 0;
        printSubset(n, cur + 1);
    }
}
