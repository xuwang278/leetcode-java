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
    // T: O (n^2)
    // S: O(logn) - O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    private TreeNode dfs(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int idx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart])
                idx = i;
        }
        
        root.left = dfs(preorder, inorder, preStart + 1, inStart, idx - 1);
        root.right = dfs(preorder, inorder, preStart + idx - inStart + 1, idx + 1, inEnd);
        // idx - inStart - length of left subtree
        return root;
    }

	// Sol 2:
    private int pre_idx;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	pre_idx = 0;
    	map = new HashMap<>();
    	for (int i = 0; i < inorder.length; i++)
    		map.put(inorder[i], i);

        return dfs(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int lo, int hi) {
    	if (lo > hi) return null;

    	TreeNode root = new TreeNode(preorder[pre_idx]);
    	int index = map.get(preorder[pre_idx]);
        pre_idx++;

        // 找左, 右子树所对应的inorder区间
    	root.left = dfs(preorder, inorder, lo, index - 1);
    	root.right = dfs(preorder, inorder, index + 1, hi);
        return root;
    }






}