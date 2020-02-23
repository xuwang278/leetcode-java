class Solution {
    // Solution 1:
    // T: O(n)
    // S: O(n)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length < 2) return nums[0];

        int[] fromFir = new int[nums.length + 1];
        int[] fromSec = new int[nums.length + 1];

        fromFir[0] = 0; // haven't started yet
        fromFir[1] = nums[0]; // rob 1st house
        fromSec[0] = 0; // haven't started yet
        fromSec[0] = 0; // skip 1st house

        for (int i = 2; i <= nums.length; i++) {
            fromFir[i] = Math.max(fromFir[i - 1], fromFir[i - 2] + nums[i - 1]);
            fromSec[i] = Math.max(fromSec[i - 1], fromSec[i - 2] + nums[i - 1]);
        }

        return Math.max(fromFir[nums.length - 1], fromSec[nums.length]); // fromFir[nums.length] is invalid
    }

    // Solution 2:
    // T: O(n)
    // S: O(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length < 2) return nums[0];
        
        int[] nums1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] nums2 = Arrays.copyOfRange(nums, 1, nums.length);

        return Math.max(robHelper(nums1), robHelper(nums2));

    }

    private int robHelper(int[] nums) {
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