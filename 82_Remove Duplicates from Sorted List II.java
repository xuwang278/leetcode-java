class Solution {
    // T: O(n)
    // S: O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
   
        while (cur.next != null && cur.next.next != null) {
        	if (cur.next.val == cur.next.next.val) {
        		int val = cur.next.val;
        		while (cur.next != null && cur.next.val == val)
        			cur.next = cur.next.next;
        	} else cur = cur.next;
        }
        return dummy.next;
    }

}