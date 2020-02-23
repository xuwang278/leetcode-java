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
    // Solution 2: for Binary Search Tree
    // look for a node from which p and q diverse into two different paths (one > node, one < node)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val > p.val && root.val > q.val) 
            return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }

	// Solution 1: for Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        if (l != null && r != null) return root; // 从root开始能够找到p和q, 当前root就是ancestor 
        if (l == null && r == null) return null; // 找不到p和q
        return l != null ? l : r; // return un-null node 这个分枝能够找到p或者q
    }

    // Solution 3: iterative
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	int pVal = p.val;
    	int qVal = q.val;
    	TreeNode x = root;
    	while (x != null) {
    		int parentVal = x.val;
    		if (pVal > parentVal && qVal > parentVal)
    			x = x.right;
    		else if (pVal < parentVal && qVal < parentVal)
    			x = x.left;
    		else return x;
    	}
    	return null;
    }
}