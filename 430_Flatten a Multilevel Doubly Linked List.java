class Solution {
	// Solution 1: Recursion
	private Node pre = null;

    public Node flatten(Node head) {
        if (head == null) return null;

        if (pre != null) {
        	pre.next = head;
        	head.prev = pre;
        }

        pre = head;
        Node next = head.next;
        flatten(head.child);
        head.child = null;
        flatten(next);
        return head;
    }

    // Solution 2: Stack
    public Node flatten(Node head) {
        if (head == null) return null;
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                if (cur.next != null) stack.push(cur.next); // put next one on stack for recall
                cur.next = cur.child; // move child up
                cur.child.prev = cur;
                cur.child = null;
            } else if (cur.next == null && !stack.isEmpty()) {
                cur.next = stack.pop(); // move child level up
                cur.next.prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    // Good practice?
    public Node flatten(Node head) {
    	if (head == null) return head;
    	Stack<Node> stack = new Stack<>();
    	Node cur = head;
    	while (cur != null) {
    		if (cur.child != null) {
    			if (cur.next != null) stack.push(cur.next);
    			cur.next = cur.child;
    			if (cur.next != null) 
    				cur.next.prev = cur;
    			cur.child = null;
    		} else if (cur.next == null && !stack.isEmpty()) {
    			cur.next = stack.pop();
    			if (cur.next != null) 
    				cur.next.prev = cur;
    		}

    		cur = cur.next;
    	}
    	return head;
    }







}