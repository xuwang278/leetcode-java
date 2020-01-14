class Solution {
    // Time: O(nlogn)
	// Space: O()
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) return null;
		return toBST(head, null);
	}

	private TreeNode sortedListToBST(ListNode head, ListNode tail) {
		if (head == tail) return null;
		
		ListNode slow = head, fast = head; // slow is mid
		while (fast != tail && fast.next != tail) {
			fast = fast.next.next;
			slow = slow.next;
		}
		TreeNode node = new TreeNode(slow.val); 
		node.left = sortedListToBST(head, slow);
		node.right = sortedListToBST(slow.next, tail);
		return node;
	}

	// Sol 2: i
	public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        ListNode slow = head, fast = head, last = null;
        while (fast != null && fast.next != null) {
            last = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // head -> 1st half
        // fast -> 2nd half
        // slow -> mid, where break the original list
        fast = slow.next;
        last.next = null;
        
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(fast);
        
        return root;
    }


    // Sol 3: reduce to 108
    public TreeNode sortedListToBST(ListNode head) {
		if (head == null)
			return null;

		ListNode cur = head;
		int count = 0;
		ArrayList<Integer> aux = new ArrayList<>();
		while (cur != null) {
			count++;
			aux.add(cur.val);
			cur = cur.next;
		}

		int[] nodes = new int[count];
		int i = 0;
		for (Integer k : aux) {
			nodes[i++] = k;
		}

		return sortedArrayToBST(nodes, 0, nodes.length - 1);

	}

	private TreeNode sortedArrayToBST(int[] nums, int low, int high) {
		if (low > high)
			return null;
		int mid = low + (high - low) / 2;
		TreeNode node = new TreeNode(nums[mid]);
		node.left = sortedArrayToBST(nums, low, mid - 1);
		node.right = sortedArrayToBST(nums, mid + 1, high);
		return node;
	}
}