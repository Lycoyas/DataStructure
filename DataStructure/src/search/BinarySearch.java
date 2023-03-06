package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[]=new int[]{1,8, 10, 89,89,89,89, 1000, 1234};

        //int resIndex = binarySearch(arr, 0, arr.length - 1, 2);
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 89);
        System.out.println(integers);
    }

    //二分查找
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if(left>right){
            return -1;
        }
        int mid=left+(right-left)/2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        }else if (findVal < midVal) {
            return binarySearch(arr, left, mid-1, findVal);
        }else{
            return mid;
        }
    }

    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到 mid 索引值，不要马上返回
     * 2. 向 mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合 ArrayList
     * 3. 向 mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合 ArrayList
     * 4. 将 Arraylist 返回
     */

    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        if(left>right){
            return null;
        }
        int mid=left+(right-left)/2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        }else if (findVal < midVal) {
            return binarySearch2(arr, left, mid-1, findVal);
        }else{
            List<Integer> resIndexList = new ArrayList<>();
            int temp=mid-1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp--);
            }
            resIndexList.add(mid);
            temp=mid+1;
            while (true) {
                if (temp == arr.length || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp++);
            }
            return resIndexList;
        }

    }

}
