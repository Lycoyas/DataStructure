package basic;

/**
 * @author Lycoyas
 * @create 2023-02-11 11:55
 */
public class ReverseList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


  ListNode successor=null;
    ListNode reverseN(ListNode head, int n) {

        if (n == 1) {
            successor=head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next=head;
        head.next = successor;
        return last;

    }

    ListNode reverseBetween(ListNode head, int m, int n){
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
