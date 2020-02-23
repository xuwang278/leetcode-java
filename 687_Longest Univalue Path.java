class Solution {
    // latest version:
    private int ans = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans == 0 ? 0 : ans - 1;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);

        int pl = 0, pr = 0;
        if (root.left != null && root.left.val == root.val) pl += l; // 如果不和 root.val 相同, 那么往上的贡献就是0
        if (root.right != null && root.right.val == root.val) pr += r;
        ans = Math.max(ans, pl + pr + 1); // 算上一个root节点
        return Math.max(pl, pr) + 1; // 向上返回最长的一枝
    }

    // previous version
    // This is an easy problem but I believe it's at least medium
    private int max = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }
    
    // postorder
    private int dfs(TreeNode root) {
        if (root == null) return -1;
        int l = dfs(root.left); // length of path ended up with root.left
        int r = dfs(root.right); // length of path ended up with root.right
        
        int pl = 0, pr = 0; // length from l/r extending to root

        // plus edge between root and root.left/root.right; otherwise, pl/pr = 0
        if (root.left != null && root.val == root.left.val) pl = l + 1; 
        if (root.right != null && root.val == root.right.val) pr = r + 1; 

        max = Math.max(max, pr + pl); // currently, ans can be the arc "l-root-r" (local) or larger one at other places
        return Math.max(pl, pr); // return the longer path because arc can occur once
    }

    
}