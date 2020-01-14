class Solution {
     1
    / \
   2   5
  / \   \
 3   4   6

postorder (right, left, root): 6 5 4 3 2 1
preorder (root, left, right):  1 2 3 4 5 6

	// T: O(n)
    // S: O(n)
    // postorder traverse
	private TreeNode pre = null; // should not pass in recursion because it is a reference instead of array/map/set

    public void flatten(TreeNode root) {
    	if (root == null) return; 
    	flatten(root.right);
    	flatten(root.left);
    	root.right = pre;
    	root.left = null;
    	pre = root;
    }

    // version 2
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        
        flatten(left);
        flatten(right);
        
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        
        // cur points last node 
        cur.right = right;
    }

}

 
