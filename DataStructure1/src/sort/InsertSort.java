package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{101,34,119,1};
        insertSort(arr);
    }

    //插入排序
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex=i-1;
            while(insertIndex>=0&&insertVal < arr[insertIndex]){

                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;

            }

            if (insertIndex + 1 != i) {
                arr[insertIndex+1]=insertVal;
            }

        }
        System.out.println(Arrays.toString(arr));
    }
}
