/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	// Solution 1: using stack to match idea as LC 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        ListNode dummy = new ListNode(-1);
        // ListNode cur = dummy;
        int leading = 0;

        while (!s1.isEmpty() || !s2.isEmpty()) {
            int a = !s1.isEmpty() ? s1.pop() : 0;
            int b = !s2.isEmpty() ? s2.pop() : 0;
            int sum = (a + b + leading) % 10;
            leading = (a + b + leading) / 10;
            ListNode oldHead = dummy.next; // adding to head of list
            dummy.next = new ListNode(sum);
            dummy.next.next = oldHead;   
        }

        if (leading != 0) {
            ListNode oldHead = dummy.next;
            dummy.next = new ListNode(leading);
            dummy.next.next = oldHead;   
        }
            
        return dummy.next;
    }

    // Solution 2: Recursion
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	int len1 = getLength(l1);
    	int len2 = getLength(l2);

    	ListNode head = new ListNode(1);
    	head.next = len1 < len2 ? addTwoNumbers(l2, l1, len2 - len1) : addTwoNumbers(l1, l2, len1 - len2);
    	
    	if (head.next.val > 9) {
    		head.next.val = head.next.val % 10;
    		return head;
    	}

    	return head.next;
    }

    private int getLength(ListNode node) {
    	int cnt = 0;
    	while (node != null) {
    		node = node.next;
    		cnt++;
    	}
    	return cnt;
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, int offset) {
    	if (l1 == null) return null;

    	 // check whether l1 becomes the same length as l2
    	ListNode result = offset == 0 ? new ListNode(l1.val + l2.val) : new ListNode(l1.val);
    	ListNode post = offset == 0 ? addTwoNumbers(l1.next, l2.next, 0) : addTwoNumbers(l1.next, l2, offset - 1);

    	// handle carry 
        if (post != null && post.val > 9) {
            result.val += 1;
            post.val = post.val % 10;
        }
        // combine nodes
        result.next = post;
        return result;
    }













}