package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{-9, 78, 0, 23, -567, 70};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr,int L,int R) {

        if(L>=R) return;

        int left=L,right=R;
        int pivot = arr[left];

        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            if(left<right){
                arr[left] = arr[right];
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if(left<right){
                arr[right] = arr[left];
            }
            if(left>=right){
                arr[left]=pivot;
            }
        }
        quickSort(arr,L,right-1);
        quickSort(arr,right+1,R);
    }



}
