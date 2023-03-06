package tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int arr[] = new int[]{4, 6, 8, 5, 9,5,1,2,7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    //堆排序
    public static void heapSort(int arr[]) {
        int temp=0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     *
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整，length是在逐渐的减少
     */
    public  static void adjustHeap(int arr[],int i,int length){

        int temp=arr[i];

        //开始调整 k=i*2+1是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k+1<length&&arr[k] < arr[k + 1]) {//i结点的左子结点 < 右子结点
                k++;//k指向右子结点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k]; //较大的值赋给当前结点，
                i=k; //i指向k继续循环比较
            }else{
                break;
            }
            arr[i] = temp;
        }
        //for循环结束后，已经将以i为父结点的树的最大值，放在了i的位置上

    }

}


