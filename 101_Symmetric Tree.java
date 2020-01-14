class Solution {
	// T: O(n)
	// S: O(n)
	// DFS
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

	private boolean dfs(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) return true;
		if (root1 == null || root2 == null) return false;
		if (root1.val != root2.val) return false;
		else return dfs(root1.left, root2.right) && 
			        dfs(root1.right, root2.left);
	}

	// BFS
	public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while (!q.isEmpty()) {
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (n1.val != n2.val) return false;
            q.offer(n1.left);
            q.offer(n2.right);
            q.offer(n1.right);
            q.offer(n2.left);
        }
        return true;
    }

    // BFS
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		if (root.left == null && root.right == null) return true;
		if (root.left == null || root.right == null) return false;
	 	
		List<List<TreeNode>> list = new LinkedList<>();
	    Queue<TreeNode> q = new LinkedList<>();
	 	q.offer(root.left);
	 	q.offer(root.right); // start from 2nd level
	 	while (!q.isEmpty()) {
	 		int size = q.size();
	 		List<TreeNode> subList = new LinkedList<>();
	 		for (int i = 0; i < size; i++) {
	 			TreeNode cur = q.poll(); // may put null to subList
	 			subList.add(cur);
	 			if (cur != null) {
	 				q.offer(cur.left);
	 				q.offer(cur.right);
	 			}
	 		}
	 		list.add(subList);
	 	}

	 	for (List<TreeNode> l : list) {
	 		if (l.size() % 2 != 0) return false;
	 		
	 		while (!l.isEmpty()) {
	 			TreeNode head = l.remove(0);
	 			TreeNode tail = l.remove(l.size() - 1);
	 			if (head == null && tail == null) continue; // cannot determine so far, ignore
	 			if (head == null || tail == null) return false;
	 			if (head.val != tail.val) 
	 				return false;	
	 		}
	 	}

	 	return true;
	}
		
}	 		
	 		