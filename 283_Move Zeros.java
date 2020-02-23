class Solution {
    // SOL 1: 扫描到非0数, 依次从左到右放进array; 然后用0补齐剩余位

    public void moveZeroes(int[] nums) {
    	if (nums == null || nums.length == 0) return;

    	// Shift non-zero values to left side
    	int insertPos = 0;
    	for (int num : nums) {
    		if (num != 0) nums[insertPos++] = num;
    	}
    	
    	// Fill remaining space with zeros
    	while (insertPos < nums.length) {
        	nums[insertPos++] = 0;
    	}

    }

    // sol 2: swap non-0 to left
    public void moveZeroes(int[] nums) {
    	int j = 0;
    	for (int i = 0; i < nums.length; i++) {
        	if(nums[i] != 0) {
            	swap(nums, i, j);
            	j++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }

}