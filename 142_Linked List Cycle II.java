public class Solution {
    // T: O(n)
    // S: O(n)
    public ListNode detectCycle(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            if (!list.contains(cur)) {
                list.add(cur);
                cur = cur.next;
            } else {
                return list.get(list.indexOf(cur));
            }
        }
        return null;
    }

    // T: O(n)
    // S: O(1)
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // count again
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    
}