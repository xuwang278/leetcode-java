class Solution {
    // T: O(n)
    // S: O(h)
    private int max = 0; // # of nodes along the longest diameter path
    
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max == 0 ? 0 : max - 1;
    }
    
    // update max and return the # of nodes along the longer path to root
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int l = dfs(root.left);
        int r = dfs(root.right);
        max = Math.max(max, l + r + 1);
        return Math.max(l, r) + 1;
    }



    // version 2: return # of nodes
    // 树类问题，递归思想，即如何通过获得相同且小规模的局部解来构建全局解
    private int ans = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans == 0 ? 0 : ans - 1;
    }
    
    // max # of nodes from bottom (null) to root along a path that only 
    // select one edge between levels
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        ans = Math.max(ans, l + r + 1);
        return Math.max(l + 1, r + 1);
    }

    // version 1: return # of edges
    private int ans = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return -1;
        int l = dfs(root.left);
        int r = dfs(root.right);
        
        int pl = l + 1, pr = r + 1;
        ans = Math.max(ans, pl + pr);
        return Math.max(pl, pr);
    }
}