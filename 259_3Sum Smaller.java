public class Solution {
    和Leetcode 15 3Sum相似
    相同点：(1) sort array (2) two pointers
    不同点:（1）3sum 寻找 snums[lo] + nums[hi] == nums[i],
               如果 < target, lo++ (逐步逼近target, 要尝试下一个最近的点，即lo + 1; 
                                    若hi--，和可能会大于target很多, 遗漏中间点)
               如果 > target, hi-- (逐步逼近target, 要尝试下一个最近的点，即hi - 1)

           (2) 3Sum Smaller：寻找 nums[lo] + nums[hi] < target - nums[i],
               如果 < target - nums[i]，那么lo - hi间的值都符合要求；并lo++；
               如果 > target - nums[i]，那么 hi--
           (3) 3Sum考虑去重复，3Sum Smaller不用

    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[i] + nums[lo] + nums[hi] < target) {
                    ans += hi - lo;
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return ans;
    }
}