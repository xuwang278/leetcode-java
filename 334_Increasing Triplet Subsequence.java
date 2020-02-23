class Solution {
	// Solution 1: Greedy
	// T: O(n)
	// S: O(1)
    public boolean increasingTriplet(int[] nums) {
        if(nums.length <= 2) return false;
        // maintain two smallest #, min1 < min2
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int num : nums) {
        	int curr = num;
        	if (curr > min2) return true;
        	else if (curr < min1) min1 = curr;
        	else if (curr > min1 && curr < min2) min2 = curr;
        }
    	return false;
    }

    // Solution 2: DP？转移方程？
	// T: O(n)
	// S: O(n)
    public boolean increasingTriplet(int[] nums) {
    	int[] dp = new int[nums.length];
    	// dp[i]: # of increasing elements ends at nums[i]
    	dp[0] = 1;
    	for (int i = 1; i < nums.length; i++) {
    		if (nums[i] > nums[j]) dp[i] = dp[i - 1] + 1
    		if (dp[i] >= 3) return true;
    	}
    	return false;
    }
}