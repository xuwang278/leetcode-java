class Solution {
    // Solution 1:
    // Recursion
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return rob(nums, nums.length - 1);
    }

    private int rob(int[] nums, int i) {
        if (i < 0) return 0;
        return Math.max(nums[i] + rob(nums, i - 2), rob(nums, i - 1));
    }

    // Solution 2:
    // Recursion w/ memorization (Top-Down DP)
    // T: O(n)
    // S: O(n)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // dp[i]: 偷完nums[i]时刻的最大收益
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return rob(nums, nums.length - 1, dp);
    }

    private int rob(int[] nums, int i, int[] dp) {
        if (i < 0) return 0;
        if (dp[i] != -1) return dp[i];
        int sum = Math.max(nums[i] + rob(nums, i - 2, dp), rob(nums, i - 1, dp));
        dp[i] = sum;
        return sum;
    }

    // Solution 3:
    // Interative w/ memorization Bottom-Up (DP)
    // T: O(n)
    // S: O(n)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        // dp[i]: 偷完第i家时刻的最大收益
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            // 到该偷第i家时，可以选择不偷（继承上次成果），或者偷但不偷上家要偷上上家
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]); 
        }
        return dp[nums.length];
    }

    // Solution 4:
    // Interative w/ memorization Bottom-Up (DP)
    // T: O(n)
    // S: O(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int pre = 0;
        int prepre = 0;
        for (int num : nums) {
            int temp = pre;
            pre = Math.max(pre, prepre + num);
            prepre = temp;
        }
        return pre;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int prepre = 0;
        int pre = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int temp = pre;
            pre = Math.max(pre, prepre + nums[i - 1]);
            prepre = temp;
        }
        return pre;
    }

}