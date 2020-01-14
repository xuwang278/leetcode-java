
class Solution {
    // T: O(n)
    // S: O(n)
    public boolean hasPathSum(TreeNode root, int sum) {
    	if (root == null) return false;
    	if (root.left == null && root.right == null && root.val == sum) // leaf
    		return true;

    	return hasPathSum(root.left, sum - root.val) 
    		   || hasPathSum(root.right, sum - root.val);
    }


}