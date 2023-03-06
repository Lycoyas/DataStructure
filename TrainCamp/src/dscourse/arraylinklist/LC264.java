package dscourse.arraylinklist;

/**
 * @author Lycoyas
 * @create 2023-02-22 15:04
 */
public class LC264 {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(11));
    }
    public static int nthUglyNumber(int n) {

        int p2=1,p3=1,p5=1;
        int product2=1,product3=1,product5=1;
        int[] ugly=new int[n+1];

        int p=1;
        while(p<=n){
            int min=Math.min(product2,Math.min(product3,product5));
            ugly[p]=min;
            if(min==product2){
                product2=p2*2;
                p2++;
            }
            if(min==product3){
                product3=p3*3;
                p3++;
            }
            if(min==product5){
                product5=p5*5;
                p5++;
            }
            p++;

        }
        return ugly[n];

    }
}
