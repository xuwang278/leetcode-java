class Solution {
	// T: O(n)
	// S: O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
        	if (l1.val > l2.val) {
        		cur.next = l2;
        		l2 = l2.next;
        	} else {
        		cur.next = l1;
        		l1 = l1.next;
        	}
      		cur = cur.next;
        }

        if (l1 == null) cur.next = l2;
        if (l2 == null) cur.next = l1;

        return dummy.next;
    }

    // time: O(n)
	// space: O(n)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
		if (l2 == null) return l1;

        // keep the smaller and sort out a smaller case
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1; // modify it and return it
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }

}