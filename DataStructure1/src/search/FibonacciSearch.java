package search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[]=new int[]{1,8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr,189));
    }

    //mid=low+F(k-1)-1

    public static int[] fib() {
        int[] f=new int[maxSize];
        f[0]=1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //斐波那契查找
    //非递归
    public static int fibSearch(int[] arr, int key) {
        int low=0;
        int high = arr.length - 1;

        int k=0;//表示斐波那契分割数值的下标
        int mid=0;

        int f[] = fib();

        //获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        //f[k]的值可能大于arr的长度，所以要构造新的数组
        //不足部分用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上需求使用 a 数组最后的数填充 temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high=mid-1;
                k--;
            } else if (key > temp[mid]) {
                low=mid+1;
                k-=2;
            }else{
                if (mid <= high) {
                    return mid;
                }else{
                    return high;
                }
            }
        }

        return -1;

    }

}
