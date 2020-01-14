class Solution {
	// T: O(n)
    // S: O(1)
    public ListNode deleteDuplicates(ListNode head) {
    	if (head == null || head.next == null) return head;

        ListNode cur = head;
        while (cur.next != null) {
        	if (cur.val == cur.next.val) 
        		cur.next = cur.next.next;
        	else cur = cur.next;
        }

        return head;
    }
}