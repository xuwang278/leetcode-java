class Solution {
    // T: O(n)
    // S: O(n)
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        // use equals() to compare
        for (int i = 0; i < list.size() / 2; i++) {
            Integer a = list.get(i);
            Integer b = list.get(list.size() - i - 1);
            if (!a.equals(b)) return false;
        }
        
        return true;
    }

    // Sol 2: 
    // Time: O(n)
    // Space: O(1)
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) return false;

            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}



