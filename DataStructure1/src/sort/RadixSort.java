package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = new int[]{53, 3, 542, 748, 14, 214};

        radixSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    //基数排序

    public static void radixSort(int[] arr) {

        //找到数组最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();


        //二维数组表示十个桶
        //防止数据溢出，一维数组大小：arr.length
        int[][] bucket = new int[10][arr.length];

        //记录每个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n=1; i < maxLength; i++,n*=10) {

            for (int j = 0; j < arr.length; j++) {

                int digitOfElement = arr[j]/n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]++] = arr[j];

            }
            int index=0;
            //遍历每一个桶，将桶中数据按下标依次放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++]=bucket[k][l];
                    }
                    bucketElementCounts[k]=0;
                }
            }
        }
    }
}
