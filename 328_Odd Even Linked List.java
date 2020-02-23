class Solution {
    public ListNode oddEvenList(ListNode head) {
    	if (head == null || head.next == null || head.next.next == null)
    		return head;

    	ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        ListNode odd = head;
        while (odd != null) {
        	cur.next = new ListNode(odd.val);
        	cur = cur.next;
        	if (odd.next == null) break;
        	odd = odd.next.next;
        }

        ListNode even = head.next;
        while (even != null) {
        	cur.next = new ListNode(even.val);
        	cur = cur.next;
        	if (even.next == null) break;
        	even = even.next.next;
        }

        return dummy.next;
    }

    // In-place
    public ListNode oddEvenList(ListNode head) {
    	if (head == null) return null;
    	ListNode odd = head, even = head.next, evenHead = even;
    	while (even != null && even.next != null) {
    		odd.next = even.next;
    		odd = odd.next;
    		even.next = odd.next;
    		even = even.next;
    	}
    	odd.next = evenHead;
    	return head;
    }

}