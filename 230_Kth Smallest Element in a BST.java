class Solution {
	
	// Solution 1: left-root-right inorder traversal
	private int count;
	private int res;

    public int kthSmallest(TreeNode root, int k) {
        // count = 0;
        // res = 0;
    	dfs(root, k);
    	return res;
    }

    private void dfs(TreeNode root, int k) {
    	if (root == null) return;
    	dfs(root.left, k);

    	count++;
    	if (count == k) {
    		res = root.val;
    		return;
    	}
    	
    	dfs(root.right, k);
    }

    // Solution 2: in-order iterative
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (k != 0) {
            TreeNode cur = stack.pop();
            k--;
            if (k == 0) return cur.val;
            TreeNode right = cur.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }

}