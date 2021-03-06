class Solution {
	// T: O(n)
	// S: O(1)

	1  2  7  4  3  1  
	   ^ (firstSmall)

	1  2  7  4  3  1
	            ^ (firstLarge) > nums[firstSmall]

	1  3  7  4  2  1 (swap(firstSmall, firstLarge)) 

	1  3  1  2  4  7 (reverse(firstSmall + 1, nums.length - 1))

    

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int firstSmall = -1; 
        for (int i = nums.length - 2; i >= 0; i--) {
        	if (nums[i] < nums[i + 1]) {
        		firstSmall = i;
        		break;
        	}
        }

        // not found, i.e. case: 3, 2, 1 -> 1, 2, 3
        if (firstSmall == -1) {
        	reverse(nums, 0, nums.length - 1);
        	return;
        }

        int firstLarge = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
        	if (nums[i] > nums[firstSmall]) {
        		firstLarge = i;
        		break;
        	}
        }

        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
    }

	private void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i++, j--);
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
}