package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{
                8,9,1,7,2,3,5,4,6,0
        };
        shellSort2(arr);
    }

    //希尔排序
    //交换法
    public static void shellSort(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的元素，说明要交换
                    if(arr[j]>arr[j+gap]){
                        int temp= arr[j];
                        arr[j] = arr[j + gap];
                        arr[j+gap]=temp;
                    }

                }
            }

        }
        System.out.println(Arrays.toString(arr));
    }

    //移位法
    public static void shellSort2(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j=i;
                int temp=arr[j];
                if(arr[j]<arr[j-gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=temp;
                }
            }


        }
        System.out.println(Arrays.toString(arr));
    }
}
