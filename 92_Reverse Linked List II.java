class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0); // in case m == 1, pre = null makes null pointer
        dummy.next = head;
        ListNode pre = dummy, lo = head, hi = head;
        for (int i = 1; i < m; i++) {
        	pre = lo;
        	lo = lo.next;
        }
        for (int i = 1; i < n; i++) {
        	hi = hi.next;
        }
        // place lo after hi
        while (lo != hi) {
        	pre.next = lo.next;
        	lo.next = hi.next;
        	hi.next = lo;
        	lo = pre.next;
        }
        return dummy.next;
    }
}