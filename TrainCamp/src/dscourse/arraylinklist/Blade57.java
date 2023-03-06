package dscourse.arraylinklist;

/**
 * @author Lycoyas
 * @create 2023-02-22 14:00
 */
public class Blade57 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println(ints[0]+" "+ints[1]);
    }

    public static int[] twoSum(int[] nums, int target) {

        int left=0,right=nums.length-1;

        int num;
        while(left<right){
            num=nums[left]+nums[right];
            if(target<num){
                right--;
            }else if(target>num){
                left++;
            }else{
                break;
            }
        }

        return new int[]{nums[left],nums[right]};

    }
}
