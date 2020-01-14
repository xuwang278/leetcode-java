
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        
        // at least two nodes left
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            ListNode nextNext = cur.next.next;
            pre.next = next;
            next.next = cur;
            cur.next = nextNext;
            
            pre = cur; // reposition
            cur = cur.next;
        }
        return dummy.next;
    }
}