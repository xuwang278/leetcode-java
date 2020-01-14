class Solution {
	// T: O(n)
	// S: O(h)
    private int max = Integer.MIN_VALUE; // don't initialize as 0 as nodes may be neg
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left); // contribution from left side (left_gain)
        int r = dfs(root.right);
        if (l < 0) l = 0; 
        if (r < 0) r = 0;
        max = Math.max(max, l + root.val + r);
        return Math.max(l, r) + root.val; // return one edge
    }

    // version 2
    private int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        int sum = findMax(l + root.val + r, l + root.val, r + root.val, root.val);
        max = Math.max(max, sum);
        return Math.max(root.val, Math.max(l + root.val, r + root.val));
    }

    private int findMax(int a, int b, int c, int d) {
        return Math.max(a, Math.max(b, Math.max(c, d)));
    }
}