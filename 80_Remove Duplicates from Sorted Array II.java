class Solution {
	// T: O(n)
	// S: O(1)
	// invariant: no duplicated in nums.subarray(0, v)
    public int removeDuplicates(int[] nums) {
    	if (nums == null ) return 0;
    	if (nums.length <= 2) return nums.length;

        int v = 2;
        for (int i = 2; i < nums.length; i++) {
        	//ignore nums[v-1], allowing duplicated keys
        	if (nums[i] != nums[v - 2]) 
        		nums[v++] = nums[i];
        }
        return v;
    }
}