class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int sum = 0;
        for (int n : nums) {
            if (n == 1) {
                sum += 1;
                res = Math.max(res, sum);
            } else sum = 0;
        }
        return res;
    }
}