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
    // Sol 1
    // T: O(n)
    // S: O(n)
    int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MAX_VALUE);
    }
    
    private TreeNode build(int[] A, int bound) {
        if (i == A.length || A[i] > bound) return null;
        TreeNode root = new TreeNode(A[i++]);
        root.left = build(A, root.val); // vals in left should be less than root.val
        root.right = build(A, bound); // vals in right should be less than 
        return root;
    }

    // Sol 2: regular bst insert
    // T: O(nh)
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++)
            root = insert(root, preorder[i]);
        return root;
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val < val) root.right = insert(root.right, val);
        else if (root.val > val) root.left = insert(root.left, val);
        else root.val = val;
        return root;
    }

    // Sol 3: use queue
    public TreeNode bstFromPreorder(int[] preorder) {
        Queue<Integer> q = new LinkedList<>();
        for (int n : preorder) q.offer(n);
        return build(q, Integer.MAX_VALUE);
    }
    
    private TreeNode build(Queue<Integer> q, int bound) {
        if (q.isEmpty() || q.peek() > bound) return null;
        int val = q.poll();
        TreeNode node = new TreeNode(val);
        node.left = build(q, val);
        node.right = build(q, bound);
        return node;
    }

}


