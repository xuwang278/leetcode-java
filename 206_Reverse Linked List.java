class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            // if (newHead == null) {
            //     newHead = cur;
            //     cur.next = null;
            // }
            // else {
                cur.next = newHead;
                newHead = cur;
            // }
            cur = next;
        }
        return newHead;
        
    }

    // Best Solution: iterate through original sequence, adding nodes to newHead
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode newHead = null;
    	while (cur != null) {
			ListNode next = cur.next;
    		cur.next = newHead;
			newHead = cur;
    		cur = next;
    	}
    	return newHead;
    }

    // Recursive
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) 
            return head; 

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

}