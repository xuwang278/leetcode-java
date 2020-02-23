/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	private int ans;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    // # of coins to make root.val == 1
    // + move out
    // - move in
    private int dfs(TreeNode root) {
    	if (root == null) return 0;
    	int l = dfs(root.left); 
    	int r = dfs(root.right); 
    	ans += Math.abs(l) + Math.abs(r);
    	return root.val + l + r - 1;
    }
    
}