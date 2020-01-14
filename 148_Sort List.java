/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // merge sort on array - O(nlogn): T(n) = 2T(n/2) + O(n) (merge)
    // merge sort on list - O(nlogn):  T(n) = 2T(n/2) + O(n) (merge + find mid)

    // Merge Sort (Top-Down, Recursion)
    // T: O(nlogn)
    // S: O(logn)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(mid));
    }

    // time: O(n)
	// space: O(1)
    private ListNode merge(ListNode l1, ListNode l2) {
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

    // time: O(n)
	// space: O(n) 如果采用递归merge，那么总的空间复杂度是nlogn？
    public ListNode merge(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
		if (l2 == null) return l1;

        // keep the smaller and sort out a smaller case
		if (l1.val < l2.val) {
			l1.next = merge(l1.next, l2);
			return l1; // modify it and return it
		} else {
			l2.next = merge(l1, l2.next);
			return l2;
		}
    }

    // Solution 2: Merge Sort (Bottom-Up)
    // Simulate recursion using interation with no need on extra space
    // T: O(nlogn)
    // S: O(1)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l = null;
        ListNode r = null;
        ListNode tail = null;
        
        // sort sublist of size (size + 1) until size covers the whole list
        for (int size = 1; size < len; size <<= 1) {
            cur = dummy.next;
            tail = dummy;
            while (cur != null) {
                l = cur;
                r = split(l, size);
                cur = split(r, size); // // l->left spliting sublist, r->right spliting sublist, cur->rest of list
                
                ListNode[] merged = merge(l, r);
                tail.next = merged[0];
                tail = merged[1];
            }
        }
        return dummy.next;
    }
    
    // split list at size step away from head, return the second part
    private ListNode split(ListNode head, int size) {
        while (size > 1 && head != null) {
            head = head.next;
            size--;
        }
        
        ListNode rest = head != null ? head.next : null;
        if (head != null) head.next = null;
        return rest;
    }
    
    // merge two sorted lists and return head and tail of the new merged list
    private ListNode[] merge(ListNode l1, ListNode l2) {
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
        
        while (cur != null && cur.next != null) // move cur to tail
            cur = cur.next;
        
        return new ListNode[] {dummy.next, cur}; // head and tail of the merged list
    }

}