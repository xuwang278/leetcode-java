class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
 }

	 1
   /  \
  2    3
 / \  / \
4  5  6  7

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL



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

    // without dummy node
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node cur = q.poll();
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
            for (int s = 1; s < size; s++) {
                Node next = q.poll();
                cur.next = next;
                cur = cur.next;
                if (next.left != null) q.offer(next.left);
                if (next.right != null) q.offer(next.right);
            }
            cur.next = null;
        }
        return root;
    }


    // T: O(n)
    // S: O(n)
    // only for perfect tree
    public void connect(TreeLinkNode root) {
    	if (root == null) return;
        if (root.left != null)
            root.left.next = root.right;
        if (root.right != null && root.next != null)
            root.right.next = root.next.left;
        
        connect(root.left);
        connect(root.right);

    }
}

