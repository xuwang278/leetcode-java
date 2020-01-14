class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) nums[++i] = nums[j];
        }
        return i + 1;
    }


	// T: O(n)
	// S: O(1)
	// invariant: no duplicated in nums.subarray(0, v)
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int v = 1; // to be inserted
        for (int i = 1; i < nums.length; i++) {
        	// insert to v and then increment i
            if (nums[i] != nums[v - 1]) {
            	nums[v++] = nums[i];
            }
        }
        return v;
    }

}