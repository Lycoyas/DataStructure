package sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[]={3,9,-1,10,-2};

        int temp=0;
        boolean flag=false;//表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for(int j=0;j<arr.length-1-i;j++){
                if (arr[j] > arr[j + 1]) {
                    flag=true;
                    temp=arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置 flag!!!, 进行下次判断
            }
        }
        System.out.println(Arrays.toString(arr));

    }

}
