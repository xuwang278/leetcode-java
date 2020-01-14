class Solution {

    public ListNode insertionSortList(ListNode head) {
        ListNode cur = head;
        ListNode dummy = new ListNode(-1);
      
        while (cur != null) {
            ListNode next = cur.next;
            ListNode p = dummy;
            while (p != null && p.next != null && p.next.val < cur.val)
                p = p.next;
        
            // insert curr between p and p.next
            cur.next = p.next;
            p.next = cur;

            curr = next;
        }

        return dummy.next;
    }

    // T: O(n^2)
    // S: O(1)
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head, temp = null, prev = null;
        while (cur != null && cur.next != null) {
            if (cur.val <= cur.next.val) {
                cur = cur.next;
            } else {
                temp = cur.next; // lable abnormal node
                cur.next = temp.next;
                prev = dummy; // temp could be as new head
                while (prev.next.val <= temp.val) {
                    prev = prev.next;
                }
                temp.next = prev.next;
                prev.next = temp;
            }
        }
        return dummy.next;
    }


}