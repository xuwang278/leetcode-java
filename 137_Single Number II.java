class Solution {
	// https://www.youtube.com/watch?v=puXcQpwgcD0
	// int: 4 bytes -> 32 bits
    public int singleNumber(int[] nums) {
        if (numns.length == 1) return nums[0];

        int res = 0, mask = 1;
        for (int i = 0; i < 32; i++) { // each bit
        	int sum = 0;
        	for (int num : nums) {
        		sum += (num >> i) & 1; 
        		sum %= 3;
        	}
        	res = res | (sum << i);
        }
        return res;
    }
}