class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        for (int i = 0; i < k; i++) {
        	ListNode tail = head, pre = null;

	        while (tail.next != null) {
		        pre = tail;
		        tail = tail.next;
		    }
		    //now tail pointes to tail
		    ListNode oldHead = head;
		    head = tail;
		    tail.next = oldHead;
		    pre.next = null;
        }
        return head;
    }


    // T: O(n)
    // S: O(1)
    public ListNode rotateRight(ListNode head, int k) {
    	if (head == null || head.next == null) return head;
        ListNode cur = head;
        int size = 1;
        while (cur.next != null) {
            cur = cur.next;
            size++;
        }
        cur.next = head; // form a circle
        
        ListNode pre = null;
        for (int i = 0; i < size - k % size; i++) {
            pre = head;
            head = head.next;
        }
        pre.next = null; // terminate the circle
        return head; 
   }

}