class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i + k <= nums.length; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++)
                sum += nums[j];
            max = Math.max(max, sum);
        }
        return max / 1.0 / k;
    }

    // sliding window
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        int max = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return max / 1.0 / k;
    }
}