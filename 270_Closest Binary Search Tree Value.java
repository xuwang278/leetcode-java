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
    public int closestValue(TreeNode root, double target) {
        TreeNode floor = floor(root, target);
        TreeNode ceiling = ceiling(root, target);
        if (floor != null && ceiling != null) {
            int floorVal = floor.val;
            int ceilingVal = ceiling.val;
            return Math.abs(floorVal - target) < Math.abs(ceilingVal - target) ? floorVal : ceilingVal;
        } else if (floor == null) return ceiling.val;
        else if (ceiling == null) return floor.val;
        return -1;
    }

    // Make sure floor recursively calls floor() instead of ceiling()
    private TreeNode floor(TreeNode root, double target) {
        if (root == null) return null;
        double cmp = target - root.val;
        if (cmp == 0) return root;
        if (cmp < 0) return floor(root.left, target);
        TreeNode t = floor(root.right, target);
        if (t != null) return t;
        return root;
    }

    private TreeNode ceiling(TreeNode root, double target) {
        if (root == null) return null;
        double cmp = target - root.val;
        if (cmp == 0) return root;
        if (cmp < 0) {
            TreeNode t = ceiling(root.left, target);
            if (t != null) return t;
            return root;
        }
        return ceiling(root.right, target);
    }
    
	// Sol 1
	public int closestValue(TreeNode root, double target) {
	    int a = root.val;
	    TreeNode kid = target < a ? root.left : root.right;
	    if (kid == null) return a;
	    int b = closestValue(kid, target);
	    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
	}

	// Sol 2
	public int closestValue(TreeNode root, double target) {
	    int ret = root.val;   
	    while(root != null){
	        if(Math.abs(target - root.val) < Math.abs(target - ret)){
	            ret = root.val;
	        }      
	        root = root.val > target? root.left: root.right;
	    }     
	    return ret;
	}

	// Sol 3:
    
}