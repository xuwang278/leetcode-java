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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key) root.right = deleteNode(root.right, key);
        else if (root.val > key) root.left = deleteNode(root.left, key);
        else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            TreeNode t = root;
            root = min(t.right);
            root.right = deleteMin(t.right);
            root.left = t.left;
        }
        return root;
    }
    
    private TreeNode min(TreeNode x) {
        if (x.left == null) return x;
        return min(x.left);
    }
    
    private TreeNode deleteMin(TreeNode x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }
}