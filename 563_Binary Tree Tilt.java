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
    // posterorder
    private int sum = 0;
    
    public int findTilt(TreeNode root) {
        dfs(root);
        return sum;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        sum += Math.abs(l - r); // side-effect
        root.val += l + r; // update root.val
        return root.val;
        return root.val + l + r; // without changing the tree
    }
}