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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null; // do nothing on null
        // case 1: root < L 
        // get rid of root and its left subtree
        if (root.val < L) return trimBST(root.right, L, R);
        // case 2: root > R 
        // get rid of root and its right subtree
        if (root.val > R) return trimBST(root.left, L, R);

        // case 3: L <= root.val <= R
        // keep root and recirsively check subtrees
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}