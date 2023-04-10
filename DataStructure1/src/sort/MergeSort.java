package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];//归并排序需要一个额外空间
        mergeSort(arr,0,arr.length-1,temp);

        System.out.println(Arrays.toString(arr));
    }


    //归并排序

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右
            mergeSort(arr, mid+1, right, temp);
            merge(arr, left, mid, right, temp);

        }

    }

    /**
     *
     * @param arr
     * @param left 左边有序序列的初始索引
     * @param mid  中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i=left;//初始化i，左边有序序列的初始索引
        int j=mid+1; //初始化j，右边有序序列的初始索引
        int t=0; //指向temp数组的当前索引

        //1.
        //先把左右两边（有序）的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }

        //2.
        //把有剩余数据的一边依次填充到temp
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while(j<=right){
            temp[t++] = arr[j++];
        }

        //3.将temp的数组拷贝到arr
        t=0;
        int tempLeft=left;
        while (tempLeft <= right) {
            arr[tempLeft++]=temp[t++];
        }

    }
}
