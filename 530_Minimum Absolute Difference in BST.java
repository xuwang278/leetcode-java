 class Solution {
	// Solution 1: inorder dfs
    private int min = Integer.MAX_VALUE;
    private TreeNode pre = null;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        getMinimumDifference(root.left);
        if (pre != null) min = Math.min(min, root.val - pre.val); // inorder -> root > pre
        pre = root;
        getMinimumDifference(root.right);
        return min;
    }

    // a more clearer version
    TreeNode pre = null;
    int min = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (pre != null) min = Math.min(min, root.val - pre.val);
        pre = root;
        dfs(root.right);
    }
    
    // Solution 2: not a BST
    // preorder, inorder, postorder all works
    TreeSet<Integer> tree = new TreeSet<>();
    int min = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) return;
        if (!tree.isEmpty()) {
            Integer floor = tree.floor(root.val);
            Integer ceiling = tree.ceiling(root.val);
            if (floor != null) min = Math.min(min, root.val - floor);
            if (ceiling != null) min = Math.min(min, ceiling - root.val);
        }
        tree.add(root.val);
        
        dfs(root.left);
        dfs(root.right);
    }

}