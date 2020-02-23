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
	// https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
	private int ans;

    public int minCameraCover(TreeNode root) {
        ans = 0;
        return dfs(root) < 1 ? 1 : 0 + ans;
    }

    private int dfs(TreeNode root) {
    	if (root == null) return 2;
    	int l = dfs(root.left);
    	int r = dfs(root.right);

    	// set camera at parent of a leaf
    	if (l == 0 || r == 0) {
    		ans++;
    		return 1;
    	}

    	return l == 1 || r == 1 ? 2 : 0;
    }
}