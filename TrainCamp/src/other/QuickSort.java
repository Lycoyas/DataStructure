package other;

import java.util.Random;

/**
 * @author Lycoyas
 * @create 2023-04-28 16:36
 * @description
 */
public class QuickSort {

    public static void sort(int[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);

    }

    public static void sort(int[] nums, int lo, int hi) {

        if(lo<=hi){
            //<=是因为=的情况下只有[lo,hi]只有一个元素，本身就是有序的
            return;
        }

        int p = partition(nums, lo, hi);

        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);

    }

    private static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];

        //相当于pivot与区间[i,j]的数进行比较
        int i = lo + 1, j = hi;


        //i=j时，[i,j]有数还没比较，所以i>j是停止条件
        while (i <= j) {

            //为什么不是i<=hi?,因为i=hi时，i++就超出范围了
            while (i < hi && nums[i] <= pivot) {
                i++;
            }
            while (j > lo && nums[j] > pivot) {
                j--;
            }
            //到此nums[i]  >  pivot
            //    nums[j] <=  pivot

            //为什么不是i>j?
            if (i >= j) {
                break;
            }

            swap(nums, i, j);

        }

        swap(nums, lo, j);
        return j;

    }



    private static void shuffle(int[] nums) {

        Random rand = new Random();
        int n=nums.length;
        for (int i = 0; i < n; i++) {
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }


    }

    private static void swap(int[] nums, int i, int j) {
        int temp=nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



}
