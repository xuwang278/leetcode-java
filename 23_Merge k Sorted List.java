
class Solution {
    // Sol 1: pq
    // (1) insert head of each list to pq
    // (2) while pq is not empty {
           // pop the top of pq;
           // insert to ans list;
           // if (T.next != null) pq.offer(T.next);
    //     }

	// T: O(Nlogk), k is the number of linked lists
    // S: O(k) + O(n), pq + ans list
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        
        
        for (ListNode l : lists) 
            if (l != null) // a good practice: always check null before using it
                pq.offer(l); 
        
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) pq.offer(cur.next); // poll and offer
        }
        return dummy.next;

        // // without using dummy node
        // ListNode head = null;
        // ListNode tail = head;
        // while (!pq.isEmpty()) {
        //     ListNode top = pq.poll();
        //     if (head == null) {
        //         head = top;
        //         tail = top;
        //     } else {
        //         tail.next = top;
        //         tail = tail.next;
        //     }
        //     if (top.next != null) pq.offer(top.next);
        // }
        // return head;
    }

    // Sol 2:
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }
    
    // T: O(nlogk), S: O(1)
    private ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo > hi) return null;
        if (lo == hi) return lists[lo];
        if (lo + 1 == hi) return mergeTwoLists(lists[lo], lists[hi]);
        int mid = lo + (hi - lo) / 2;
        ListNode l = merge(lists, lo, mid);
        ListNode r = merge(lists, mid + 1, hi);
        return mergeTwoLists(l, r);
    }

    // LC 21, T: O(n), S: O(1)
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 == null) cur.next = l2;
        if (l2 == null) cur.next = l1;
        return dummy.next;
    }
}