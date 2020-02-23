class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int max = 1;
        int lo = 0; // start of an increasing subarray
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                max = Math.max(max, i - lo + 1);
            } else lo = i;
        }
        return max;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (nums[i - 1] > nums[i - 2])
                dp[i] = dp[i - 1] + 1;
            else dp[i] = 1;
        }
        
        int max = 0;
        for (int i : dp) max = Math.max(max, i);
        return max;
    }
}