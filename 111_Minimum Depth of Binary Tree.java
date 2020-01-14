class Solution {
	// T: O(n)
	// S: O(lgn)
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        //if (root.left == null && root.right == null) return 1;
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        if (l == 0) return r + 1;
        if (r == 0) return l + 1;
        return Math.min(l, r) + 1;
    }

	// T: O(n)
	// S: O(lgn)
	private int res = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        minDepth(root, 1);
        return res;
    }

    private void minDepth(TreeNode root, int height) {
    	if (root == null) return;

    	minDepth(root.left, height + 1);
    	minDepth(root.right, height + 1);

    	if (root.left == null && root.right == null) 
    		res = Math.min(res, height);
    }

}