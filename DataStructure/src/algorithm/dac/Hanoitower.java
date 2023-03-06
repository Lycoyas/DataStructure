package algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');

    }

    public static void hanoitower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + " -> " + c);
        }else{
            //最上面的所有盘A->B
            hanoitower(num-1,a,c,b);
            //最下面的一个盘A->C
            System.out.println("第 " + num + " 个盘从 " + a + " -> " + c);
            //B的所有盘移动到C
            hanoitower(num - 1, b, a, c);
        }
    }
}
