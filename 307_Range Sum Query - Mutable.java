// beat 25%
class NumArray {

	private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
    }
    
    // O(1)
    public void update(int i, int val) {
        nums[i] = val;
    }
    
    // O(n)
    public int sumRange(int i, int j) {
        int res = 0;
        for (int k = i; k <= j; k++) res += nums[k];
        return res;
    }
}

// beat 25%
class NumArray {

	private int[] nums;
	private int[] sums;

    public NumArray(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
    		sums[i] = sums[i - 1] + nums[i - 1];
    	}
    }
    
    // O(n)
    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        for (int k = i + 1; k < sums.length; k++)
        	sums[k] += diff;
    }
    
    // O(1)
    public int sumRange(int i, int j) {
    	return sums[j + 1] - sums[i];
    }

}

// Solution 3: https://leetcode.com/problems/range-sum-query-mutable/discuss/75724/17-ms-Java-solution-with-segment-tree

class NumArray {

	private class SegmentTreeNode {
		private int start, end;
		private int sum;
		private SegmentTreeNode left, right;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			sum = 0;
		}

	}

	private SegmentTreeNode root;

	// T: O(n)
    // S: O(n)
	public NumArray(int[] nums) {
    	root = buildTree(nums, 0, nums.length - 1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
    	if (start > end) return null;
    	SegmentTreeNode res = new SegmentTreeNode(start, end);
    	if (start == end) res.sum = nums[start];
    	else {
    		int mid = start + (end - start) / 2;
    		res.left = buildTree(nums, start, mid); // left looks after mid
    		res.right = buildTree(nums, mid + 1, end);
    		res.sum = res.left.sum + res.right.sum;
    	}
    	return res;
    }
    
    // T: O(logn)
    // S: O(logn)
    public void update(int i, int val) {
    	update(root, i, val);
    }

    private void update(SegmentTreeNode root, int i, int val) {
    	if (root.start == root.end) {
            root.sum = val; 
            return;
        }
    	
    	int mid = root.start + (root.end - root.start) / 2;
    	if (i <= mid) update(root.left, i, val);
    	else update(root.right, i, val);
    	root.sum = root.left.sum + root.right.sum;
    }
    
    // T: O(logn)
    // S: O(logn)
    public int sumRange(int i, int j) {
    	return sumRange(root, i, j);
    }

    private int sumRange(SegmentTreeNode root, int start, int end) {
    	if (root.start == start && root.end == end) return root.sum;
    	int mid = root.start + (root.end - root.start) / 2;
    	if (end <= mid) return sumRange(root.left, start, end);
    	if (start >= mid + 1) return sumRange(root.right, start, end);
    	return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
    }

}








