class Solution {
    TreeNode pre = null;
    
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null; // do nothing
        convertBST(root.right);
        if (pre != null) root.val += pre.val;
        pre = root;
        convertBST(root.left);
        return root;
    }
}