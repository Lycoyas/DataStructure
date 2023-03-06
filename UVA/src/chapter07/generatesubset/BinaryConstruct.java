package chapter07.generatesubset;

/**
 * @author Lycoyas
 * @create 2022-10-03 17:35
 */
public class BinaryConstruct {

    public static void main(String[] args) {
        int n=10;
        int sum=0;
        for (int i = 0; i < (1 << n); i++) {
            sum++;
            printSubset(n, i);
        }
        System.out.println("sum = " + sum);
    }

    static void printSubset(int n, int s) {

        for (int i = 0; i < n; i++) {
            if(((s>>i)&1)==1) System.out.print(i+" ");
        }
        System.out.println();

    }

}
