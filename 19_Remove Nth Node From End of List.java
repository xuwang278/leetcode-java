class Solution {

	// T: O(n)
	// S: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
    	ListNode tail = dummy;
    	int size = 0;
    	while (tail != null && tail.next != null) {
    		tail = tail.next;
    		size++;
    	}

    	int nth = size - n;
    	ListNode cur = dummy;
    	for (int i = 0; i < nth; i++)
    		cur = cur.next;

    	cur.next = cur.next.next;
    	return dummy.next; // corner case [1] n=1
    }

	// T: O(n)
	// S: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
    	ListNode slow = dummy, fast = dummy;
    	for (int i = 0; i <= n; i++) { // fast is n steps ahead of slow
    		fast = fast.next;
    	}

    	while (fast != null) {
			fast = fast.next;
			slow = slow.next;
    	}

    	slow.next = slow.next.next; // assume n is always valid
    	return dummy.next;
    }
}