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
	// https://www.youtube.com/watch?v=13m9ZCB8gjw
	// T: O(n)
	// S: O(h)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        // divide
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        // conquer
        // check if l or r are or ancestors of p and q 
        if (l != null && r != null) return root; 
        if (l != null) return l;
        else return r;
    }
}