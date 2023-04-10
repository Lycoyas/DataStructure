package search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        System.out.println(insertValueSearch(arr,0,arr.length-1,20));
    }

    //插值查找
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        //findVal < arr[0] || findVal > arr[arr.length - 1] 必须有，因为mid可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid-1, findVal);
        }else{
            return mid;
        }
    }

}
