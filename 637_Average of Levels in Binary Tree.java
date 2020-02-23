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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
        	int size = q.size();
        	double sum = 0;
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = q.poll();
        		sum += cur.val;
        		if (cur.left != null) q.offer(cur.left);
        		if (cur.right != null) q.offer(cur.right);
        	}
        	ans.add(sum / size);
        }
        return ans;
    }
}