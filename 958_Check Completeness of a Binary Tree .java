class Solution {
    // Sol 1:
    public boolean isCompleteTree(TreeNode root) {
        boolean end = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode top = q.poll();
            if (top == null) end = true; // 按层访问, 第一个遇到的null标志着树的结尾
            else {
                if (end) return false; // null 右面还有node, 说明incomplete
                q.offer(top.left);
                q.offer(top.right);
            }
        }
        return true;
    }

    // Sol 2:
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (q.peek() != null) {
        	TreeNode top = q.poll();
        	q.offer(top.left);
        	q.offer(top.right);
        }

        while (!q.isEmpty() && q.peek() == null)
        	q.poll();

        return q.isEmpty();
    }

    
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */