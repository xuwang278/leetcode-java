class Solution {
	// Solution 1: backtracking, dfs searching
	// T: O(2^n)
	// S: O(n)
    private int res;
    
    public int findTargetSumWays(int[] nums, int S) {
        res = 0;
        dfs(0, nums, S);
        return res;
    }
    
    private void dfs(int idx, int[] nums, int S) {
        if (idx == nums.length) {
            if (S == 0) res++;
            return;
        }

        dfs(idx + 1, nums, S + nums[idx]);
        dfs(idx + 1, nums, S - nums[idx]);
    }

    // Solution 2: Top-down DP
    // T: O(2^n)
	// S: O(n)
	public int findTargetSumWays(int[] nums, int S) {

	}

	// Solution 3: Bottom-up DP
    // T: O(sum * n)
	// S: O(sum * n)
	public int findTargetSumWays(int[] nums, int S) {
		int sum = 0;
		for (int num : nums) sum += num;
		if (sum < S) return 0;

		int n = nums.length;
		int offset = sum; // turn to 0-indexed 
		int[][] dp = new int[n + 1][2 * sum + 1];
		// dp[i][j]: # of ways to build j using first i items
		dp[0][0 + offset] = 1; 
		for (int i = 0; i < n; i++) {
			// check sum set
			for (int j = 0; j < 2 * sum + 1; j++) {
				if (dp[i][j] == 0) continue; 
				// extend
				if (j + nums[i] < 2 * sum + 1) 
					dp[i + 1][j + nums[i]] += dp[i][j];
				if (j - nums[i] >= 0) 
					dp[i + 1][j - nums[i]] += dp[i][j];
			}
		}

		return dp[n][S + offset];
	}

	// Solution 3: Bottom-up DP, pro
    // T: O(sum * n)
	// S: O(sum)
	public int findTargetSumWays(int[] nums, int S) {
		int sum = 0;
		for (int num : nums) sum += num;
		if (sum < S) return 0;

		int offset = sum; 
		int[] dp = new int[2 * sum + 1];
		dp[0 + offset] = 1; 
		for (int num : nums) {
			int[] next = new int[2 * sum + 1];
			for (int j = 0; j < 2 * sum + 1; j++) {
				if (dp[j] == 0) continue;
				if (j + num < 2 * sum + 1) 
					next[j + num] += dp[j];
				if (j - num >= 0) 
					next[j - num] += dp[j];
			}
			dp = next;
		}
		return dp[S + offset];
	}


}