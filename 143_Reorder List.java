class Solution {
    public void reorderList(ListNode head) {
    	if (head == null) return;
		ListNode l1 = head;

		// find mid
    	ListNode mid = findMid(head);
    	ListNode l2 = mid.next;
		mid.next = null; // split
		
		// reverse
		l2 = reverse(l2);
		
		// re-construct
    	while (l1 != null && l2 != null) {
    		ListNode next = l1.next;
    		l1.next = l2;
    		l2 = l2.next;
    		l1.next.next = next;
    		l1 = next;
    	}

	}
	
	// 1-2     mid = 1
	// 1-2-3   mid = 2
    private ListNode findMid(ListNode head) {
    	ListNode slow = head;
    	ListNode fast = head;
    	while (fast.next != null && fast.next.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	return slow;
    }

    private ListNode reverse(ListNode head) {
    	ListNode cur = head, newHead = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
	}

	
}