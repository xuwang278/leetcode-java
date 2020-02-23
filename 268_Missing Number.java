class Solution {
	// T: O(nlogn)
	// S: O(1) - heap sort, O(n) quick sort/merge sort
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);

        // Ensure that n is at the last index
        if (nums[nums.length - 1] != nums.length) 
            return nums.length;

        // Ensure that 0 is at the first index
        if (nums[0] != 0) 
            return 0;

        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] - nums[i - 1] != 1) 
        		return nums[i] - 1;
        }

        // Array was not missing any numbers
        return -1;
    }

    // T: O(n)
    // S: O(n)
    public int missingNumber(int[] nums) {
    	Set<Integer> set = new HashSet<>();
    	for (int n : nums) set.add(n);

    	// nums[] with length n should contain 0, 1, ... ,n with one of them absent
    	for (int i = 0; i <= nums.length; i++) {
    		if (set.contains(i))
    			return i;
    	}
    	return -1;
    }

    // T: O(n)
    // S: O(n)
    // XOR ( ^ ) 相同为零 不同为一
    public int missingNumber(int[] nums) {
    	int missing = nums.length;
    	for (int i = 0; i < nums.length; i++) {
    		missing ^= i ^ nums[i];
    	}
    	return missing;
    }
    
    0   1   2   3   4
    000 001 010 011 110

}