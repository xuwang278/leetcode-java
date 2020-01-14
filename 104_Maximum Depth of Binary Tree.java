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
    // T: O(n)
    // S: O(n)
    // DFS post-order
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        //if (root.left == null && root.right == null) return 1;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l, r) + 1;
    }
    
    // Iterative BFS 
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
    	int depth = 0;
    	Queue<TreeNode> q = new LinkedList<>();
    	q.offer(root);
    	while (!q.isEmpty()) {
    		// iterate nodes in each level
    		int size = q.size();
    		for (int i = 0; i < size; i++) {
    			TreeNode cur = q.poll();
    			if (cur.left != null) q.offer(cur.left);
    			if (cur.right != null) q.offer(cur.right);
    		}
    		depth++;
    	}
    	return depth;
    }
}

