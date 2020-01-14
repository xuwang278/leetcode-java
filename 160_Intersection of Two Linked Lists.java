public class Solution {
    // T: O(m+n)
    // S: O(m) or O(n)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }

        cur = headB;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            } else {
                cur = cur.next;
            }
        }
        return null;
    }

    // T: O(m+n)
    // S: O(1) 
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    // T: O(m+n)
    // S: O(1) 
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        ListNode curA = headA;
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        int lenB = 0;
        ListNode curB = headB;
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;
        if (lenA > lenB) {
            int dist = lenA - lenB;
            for (int i = 0; i < dist; i++) curA = curA.next;
        } else if (lenA < lenB){
            int dist = lenB - lenA;
            for (int i = 0; i < dist; i++) curB = curB.next;
        }

        while (curA != null && curB != null) {
            if (curA == curB) return curA;
            else {
                curA = curA.next;
                curB = curB.next;
            }
        }

        return null;

    }
}