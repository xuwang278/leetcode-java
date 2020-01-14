public class Solution {
	// T: O(n)
    // S: O(1)
    // works for imperfect tree
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
        	int size = q.size();
        	TreeLinkNode pre = new TreeLinkNode(-1);
        	for (int i = 0; i < size; i++) {
        		TreeLinkNode cur = q.poll();
        		if (cur.left != null) q.offer(cur.left);
        		if (cur.right != null) q.offer(cur.right);
        		pre.next = cur;
        		pre = cur;
        	}
        }
    }


    

}