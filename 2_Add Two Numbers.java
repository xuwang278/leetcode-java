class Solution {
    // Time: O(n)
    // Space: O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int leading = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = (a + b + leading) % 10;
            leading = (a + b + leading) / 10;
            cur.next = new ListNode(sum);
            
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        if (leading != 0) cur.next = new ListNode(leading);
        return dummy.next;
    }

}
