class Solution {
	// T: O(n)
    // S: O(lgn)
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
    
    private TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, lo, mid - 1);
        node.right = dfs(nums, mid + 1, hi);
        return node;
    }

    // T: O(n)
    // S: O(lgn)
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int lo = 0, hi = nums.length - 1;
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, lo, mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, hi + 1));
        return root;
    }
}