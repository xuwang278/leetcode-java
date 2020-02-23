class Solution {
    
    // Top-down DP
    public int combinationSum4(int[] nums, int target) {
    	// dp[i]: # of permutations that sum up to target
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1; // one item in nums equals to target
        return dfs(nums, target, dp);
    }
    
    private int dfs(int[] nums, int target, int[] dp) {
    	if (dp[target] != -1) return dp[target];

    	int res = 0;
    	for (int i = 0; i < nums.length; i++) {
    		if (target >= nums[i])
    			res += dfs(nums, target - nums[i], dp);
    	}

    	dp[target] = res;
    	return res;
    }

    // Bottom-up DP
    public int combinationSum4(int[] nums, int target) {
    	int[] dp = new int[target + 1];
    	dp[0] = 1;
    	for (int i = 1; i < dp.length; i++) {
    		for (int j = 0; j < nums.length; j++) {
    			if (i - nums[j] >= 0) {
    				dp[i] += dp[i - nums[j]];
    			}
    		}
    	}
    	return dp[target];
    }

    
    public int combinationSum4(int[] nums, int target) {
        // dp[i]: # of permutations that sum up to i
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return dfs(nums, target, dp);
    }
    
    private int dfs(int[] nums, int target, int[] dp) {
        if (target == 0) {
            dp[target] = 1;
            return 1;
        }
        
        if (dp[target] != -1) return dp[target];
        
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) continue;
            res += dfs(nums, target - nums[i], dp);
        }
        dp[target] = res;
        return res;
    }

}