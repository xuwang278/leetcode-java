class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        int leftSum = 0;
    	for (int i = 0; i < nums.length; i++)
    		if (leftSum == sum - leftSum - nums[i]) return i;

    	return -1;
    }
}