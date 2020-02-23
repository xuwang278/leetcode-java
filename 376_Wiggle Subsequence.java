class Solution {
	// T: O(n)
	// S: O(n)
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        
        // dp[i]: max length of wiggle sequence ending with nums[i]
        int[] dp = new int[nums.length];
        dp[0] = 1;
        dp[1] = nums[0] == nums[1] ? 1 : 2;

        int next = 0; // 0 - any difference is valid; 1 - next must be pos to be valid; -1 - must be neg
        if (nums[1] - nums[0] > 0) next = -1;
        else if (nums[1] - nums[0] < 0) next = 1;
        else next = 0;

        for (int i = 2; i < nums.length; i++) {
        	int diff = nums[i] - nums[i - 1];
        	if (diff > 0) {
        		dp[i] = next == 1 || next == 0 ? dp[i - 1] + 1 : dp[i - 1];
        		next = -1;
        	} else if (diff < 0){
        		dp[i] = next == -1 || next == 0 ? dp[i - 1] + 1 : dp[i - 1];
        		next = 1;
        	} else {
        		dp[i] = dp[i - 1];
        	}
        }
        return dp[nums.length - 1];
    }

    // T: O(n)
	// S: O(1)
    public int wiggleMaxLength(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        
        int pre = nums[0] == nums[1] ? 1 : 2;

        int next = 0; 
        if (nums[1] - nums[0] > 0) next = -1;
        else if (nums[1] - nums[0] < 0) next = 1;

        for (int i = 2; i < nums.length; i++) {
        	int diff = nums[i] - nums[i - 1];
        	if (diff > 0) {
        		pre = next == 1 || next == 0 ? pre + 1 : pre;
        		next = -1;
        	} else if (diff < 0){
        		pre = next == -1 || next == 0 ? pre + 1 : pre;
        		next = 1;
        	} 
        }
        return pre;
    }

}


	