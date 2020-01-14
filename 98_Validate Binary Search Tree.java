class Solution {
    // Time: O(n)
    // Space: O(h)
    private TreeNode pre = null;
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre == null) {
            pre = root;
        } else {
            if (pre.val >= root.val) return false;
            pre = root;
        }
        return isValidBST(root.right);
    }

    // version 2
    private long pre = Long.MIN_VALUE;
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (root.val <= pre) return false;
        pre = (long) root.val;
        if (!isValidBST(root.right)) return false;
        return true;
    }

    // Solution 2: limit the value range for the sub-trees
    // T: O(n)
    // S: O(h)
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return isValidBST(root.left, min, root.val) 
                && isValidBST(root.right, root.val, max);
    }

    // version 2
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean dfs(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min) return false;
        if (root.val >= max) return false;
        return dfs(root.left, min, (long) root.val) && dfs(root.right, (long) root.val, max);
    }   
    


    // This solution doesn't work because pre is changing dynamically.

    // Java is always pass by value, although value is reference to object.
    // When parent calls child, a copy of pre is passed to child; the copy may change,
    // pointing to a new node, however when calling back from recursion,
    // the copy of pre at parent level still points to the original place.

    // If Pre is a private field, it is not passed to any method;
    // whenever there is a change on it, the change happens just on it!

    // Merge sort: aux is passed in by value
    // change happens on different entries of the aux array, so there is no confict 
    // on the same position of the array
    public boolean isValidBST(TreeNode root) {
        TreeNode pre = null;
        return dfs(root, pre);
    }
    
    private boolean dfs(TreeNode root, TreeNode pre) {
        if (root == null) return true;
        if (!dfs(root.left, pre)) return false;
        if (pre != null && root.val <= pre.val) return false;
        pre = root;        
        return dfs(root.right, pre);
    }
    
}