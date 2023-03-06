package lc202210.lc817;

/**
 * @author Lycoyas
 * @create 2022-10-12 12:42
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

public class Solution {
    public int numComponents(ListNode head, int[] nums) {

        boolean[] exist = new boolean[10000 + 5];

        for (int i = 0; i < nums.length; i++) {
            exist[nums[i]] = true;
        }

        int count=0;

        ListNode p=head;


        while (p != null) {

            while (p != null && !exist[p.val]) {
                p = p.next;
            }
            if (p == null) {
                return count;
            }
            count++;
            while (p != null && exist[p.val]) {
                p = p.next;
            }
        }

        return count;

    }
}
