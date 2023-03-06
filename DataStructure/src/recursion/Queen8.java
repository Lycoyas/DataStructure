package recursion;

public class Queen8 {

    //皇后数量
    int max=8;
    int array[] = new int[max];

    static int count=0;
    static int judgeCount=0;

    public static void main(String[] args) {

        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d 解法", count);
        System.out.printf("一共判断冲突的次数%d 次", judgeCount); //15720
    }

    //放置第n个皇后
    private void check(int n){
        if(n==max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n]=i;
            if(judge(n)){
                check(n+1);
            }
        }

    }

    //放置第n个皇后，检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }




}
