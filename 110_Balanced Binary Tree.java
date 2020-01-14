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
	// DFS - preorder
	// T: O(n) + 2O(n/2) + 4O(n/4) + ... = (nlogn)
	// check root's two subtree's height, 
	// if valid, continue to check root's left && root'right as new root
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int l = height(root.left);
        int r = height(root.right);
        if (l - r > 1 || r - l > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    // LC 104
    // O(n)
    private int height(TreeNode root) {
        if (root == null) return 0;
        int l = height(root.left);
        int r = height(root.right);
        return 1 + Math.max(l, r);
    }

    // Solution 2:
    // T: O(n)
    private boolean balanced = true;
    
    public boolean isBalanced(TreeNode root) {
        height(root);
        return balanced;
    }
    
    private int height(TreeNode root) {
        if (root == null) return 0;
        int l = height(root.left);
        int r = height(root.right);
        if (l - r > 1 || r - l > 1) balanced = false;
        return 1 + Math.max(l, r);
    }
}