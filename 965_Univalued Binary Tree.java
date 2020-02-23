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
    private int target;
    
    public boolean isUnivalTree(TreeNode root) {
        target = root.val;
        return dfs(root);
    }
    
    private boolean dfs(TreeNode root) {
        if (root == null) return true;
        if (root.val != target) return false;
        return dfs(root.left) && dfs(root.right);
    }
}