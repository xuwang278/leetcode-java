class Solution {
	// T: O(n)
	// S: O(1)
    public ListNode partition(ListNode head, int x) {
    	if (head == null || head.next == null) return head;
       	ListNode less = new ListNode(-1);
       	ListNode more = new ListNode(-1);
       	ListNode l = less;
       	ListNode m = more;
       	ListNode cur = head;
       	while (cur != null) {
       		if (cur.val < x) {
       			l.next = cur;
       			l = l.next;
       		} else {
       			m.next = cur;
       			m = m.next;
       		}
       		cur = cur.next;
       	}
       	m.next = null;
       	l.next = more.next;
       	return less.next;
    }
}