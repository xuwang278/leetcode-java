class Solution {
	// Solution 1: DFS
	private int sum;

    public int sumOfLeftLeaves(TreeNode root) {
    	sum = 0;
    	dfs(root);
    	return sum;
    }

    private void dfs(TreeNode root) {
    	if (root == null) return;
    	if (root.left != null && root.left.left == null && root.left.right == null) 
    		sum += root.left.val;
    	dfs(root.left);
    	dfs(root.right);
    }
}

class Solution {
    // Solution 2: DFS
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) return 0;
		int res = 0;
		if (root.left != null && root.left.left == null && root.left.right == null) 
    		res += root.left.val;
		return res + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right); 
	}

	// Solutiuon 3: BFS
    public int sumOfLeftLeaves(TreeNode root) {
    	if (root == null) return 0;

    	int res = 0;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while (!q.isEmpty()) {
    		int size = q.size();
    		for (int i = 0; i < size; i++) {
    			TreeNode cur = q.poll();
    			if (cur.left != null) {
    				if (cur.left.left == null && cur.left.right == null) 
    					res += cur.left.val;
    				q.offer(cur.left);
    			} 
    			if (cur.right != null) q.offer(cur.right);
    		}
    	}
    	return res;
    }

}

	Why the following version not working?
	Because res is pass in by value: not changing its original value

	public int sumOfLeftLeaves(TreeNode root) {
    	int res = 0;
    	dfs(root, res);
    	return res;
    }

    private void dfs(TreeNode root, int res) {
    	if (root == null) return;
    	if (root.left != null && root.left.left == null && root.left.right == null) 
    		res += root.left.val;
    	dfs(root.left, res);
    	dfs(root.right, res);
    }


    Why the following version not working?
	Because wrong recursion structure
	
    public int sumOfLeftLeaves(TreeNode root) {
    	int res = 0;
    	return dfs(root, res);
    }

    private int dfs(TreeNode root, int res) {
    	if (root == null) return 0;
    	if (root.left != null && root.left.left == null && root.left.right == null) 
    		res += root.left.val;
    	return res + dfs(root.left, res) + dfs(root.right, res);
    }















