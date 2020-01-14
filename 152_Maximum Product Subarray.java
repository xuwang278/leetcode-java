class Solution {
    // DP
    // T: O(n)
    // S: O(n)
    public int maxProduct(int[] nums) {
        int[] dp_min = new int[nums.length];
        int[] dp_max = new int[nums.length];
        dp_min[0] = nums[0];
        dp_max[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // max may generated from previous min
            // to avoid 0 in nums, also consider nums[i] itself
            dp_min[i] = Math.min(nums[i], Math.min(dp_min[i - 1] * nums[i], dp_max[i - 1] * nums[i]));
            dp_max[i] = Math.max(nums[i], Math.max(dp_min[i - 1] * nums[i], dp_max[i - 1] * nums[i]));
            max = Math.max(max, dp_max[i]);
        }
        return max;
    }

    // DP (Optimized)
    // T: O(n)
    // S: O(1)
    public int maxProduct(int[] nums) {
        int cur_min = nums[0];
        int cur_max = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur_min_copy = cur_min;
            cur_min = Math.min(nums[i], Math.min(cur_min * nums[i], cur_max * nums[i]));
            cur_max = Math.max(nums[i], Math.max(cur_min_copy * nums[i], cur_max * nums[i])); // cur_min may change now, so use cur_min_copy instead
            max = Math.max(max, cur_max);
        }
        return max;
    }

}