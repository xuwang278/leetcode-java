class Solution {
	// T: O(n^2)
	// S: O(1)
    public int threeSumClosest(int[] nums, int target) {
       	if (nums == null || nums.length < 3) return 0;
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
        	int lo = i + 1, hi = nums.length - 1;
        	while (lo < hi) {
        		int sum = nums[i] + nums[lo] + nums[hi];
        		if (sum == target) return sum; // same as target
        		else if (sum < target) lo++; 
        		else hi--;

        		// maintain closest invariant
        		if (Math.abs(sum - target) < Math.abs(res - target))
        			res = sum;
        	}
        }
        return res;
    }

}