class Solution {
    // Solution 3: DFS 
    // look for a combination in nums (any sisze) that sums up to target
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        Arrays.sort(nums);
        return dfs(nums, 0, 0, target);
    }
    
    private boolean dfs(int[] nums, int start, int sum, int target) {
        if (sum > target) return false; // reject
        if (sum == target) return true; // accept
        
        // expand
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 去重, 移除对sum的相同贡献, 重要剪枝,否则超时
            sum += nums[i];
            if (dfs(nums, i + 1, sum, target)) return true; // found a subset, just return
            sum -= nums[i]; // pop
        }
        return false; // all don't work in the end, return false;
    }
    
    // https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
    // T: O(n * sum) = O(n) as sum is limited
    // S: O(n * sum) = O(n)
    public boolean canPartition(int[] nums) {
    	int sum = 0;
    	for (int n : nums) sum += n;
    	if ((sum & 1) == 1) return false; // odd sum can never partition
    	
        sum /= 2; // sum of subset
    	int n = nums.length;
    	boolean[][] dp = new boolean[n + 1][sum + 1]; // dp[i][j] == true: j can be summed up using the first i itmes in nums
    	dp[0][0] = true;
    	for (int i = 1; i <= n; i++) dp[i][0] = true; // not change result but makes code clearer
    	for (int j = 1; j <= sum; j++) dp[0][j] = false; // not necessary but makes code clearer
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= sum; j++) {
    			// for each current number, nums[i - 1], if we don't pick it, dp[i][j] = dp[i-1][j]
    			dp[i][j] = dp[i - 1][j];

                // if j is decompositable by current nums[i-1] and we may pick it up or not
                // (1) if dp[i - 1][j - nums[i - 1]] == true, then dp[i][j] == true 
                // because dp[i][j] = dp[i - 1][j - nums[i - 1]] + nums[i - 1]
                // (2) if dp[i - 1][j - nums[i - 1]] == false but dp[i - 1][j] == true
                // then dp[i][j] == true because we know previous i - 1 items can make j, i.e. current number
                // nums[i - 1] is not involved
    			if (j >= nums[i - 1]) 
    				dp[i][j] = (dp[i][j] || dp[i - 1][j - nums[i - 1]]);
    		}	
    	}
    	return dp[n][sum];
    }

    // T: O(n) 
    // S: O(sum) = O(1)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if ((sum & 1) == 1) return false; // odd sum can never partition
        
        sum /= 2; // sum of subset
        boolean dp[] = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--){
                if (i < num) continue; // inherent previous 
                dp[i] = dp[i] || dp[i - num]; // inherent backward (from far to near, "i--"")
            }
        }

        return dp[sum];
    }
    
    why inner loop is i--? 
    https://www.cnblogs.com/grandyang/p/5951422.html

    // 滚动数组
    // still j--
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        
        if ((sum & 1) == 1) return false; // odd sum can never p
        int target = sum / 2;
        int size = nums.length;
        
        boolean pre[] =  new boolean[target + 1];
        boolean next[] =  new boolean[target + 1];
        pre[0] = true;
        next[0] = true;
        
        for (int i = 1; i <= size; i++) {
            for (int j = target; j > 0; j--) {
                if (j < nums[i - 1]) next[j] = pre[j];
                else next[j] = pre[j] || pre[j - nums[i - 1]];
                
                boolean[] temp = pre;
                pre = next;
                next = pre;
            }
        }
        
        return next[target];
    }

    

    // Solution 4: DFS + Set AC
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;
        
        return dfs(nums, 0, 0, sum / 2, new HashSet<>());   
    }
    
    private boolean dfs(int[] nums, int start, int sum, int target, Set<Integer> prefixSum) {
        if (sum > target) return false;
        if (sum == target) return true;
        if (prefixSum.contains(sum)) return false; // without it, TLE; avoid duplicate calculation
        prefixSum.add(sum);
        for (int i = start; i < nums.length; i++) {
            if (dfs(nums, i + 1, sum + nums[i], target, prefixSum)) return true;
        }
        return false;
    }

}
