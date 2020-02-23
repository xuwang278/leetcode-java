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
    // Sol 1:
    // T: O(n)
    // S: O(h)
    private int sum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (L <= root.val && root.val <= R) sum += root.val;
        rangeSumBST(root.left, L, R);
        rangeSumBST(root.right, L, R);
        return sum;
    }
    
    // Sol 2:
    // T: O(n)
    // S: O(h)
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        
        // there is no chance that root.left contains any valid nodes
        if (root.val < L)
            return rangeSumBST(root.right, L, R);
        
        // there is no chance that right contains any valid nodes
        if (root.val > R)
            return rangeSumBST(root.left, L, R);
        
        // could be on both sides
        return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
    }
}
