class Solution {
	// T: O(n)
    // S: O(1)
    public void sortColors(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for (int n : nums) {
        	if (n == 0) num0++;
        	if (n == 1) num1++;
        	if (n == 2) num2++;
        }
        for (int i = 0; i < num0; i++) nums[i] = 0;
        for (int i = num0; i < num0 + num1; i++) nums[i] = 1;
        for (int i = num0 + num1; i < num0 + num1 + num2; i++) nums[i] = 2;
    }


	// T: O(n)a
    // S: O(1)
 	public void sortColors(int[] nums) { 
 		if (nums == null || nums.length == 0) return;

		// lo: next available position to insert 0
		// hi: next available position to insert 2
 		int lo = 0, hi = nums.length - 1, i = 0;
 		while (i <= hi) {
 			if (nums[i] == 0) swap(nums, i++, lo++);
 			else if (nums[i] == 1) i++;
 			else if (nums[i] == 2) swap(nums, i, hi--); // not certain on what nums[i] is after swapping (< 1 or ==1 unknown), so i stay there
 		}
 	}

 	private void swap(int[] nums, int i, int j) {
 		int temp = nums[i];
 		nums[i] = nums[j];
 		nums[j] = temp;
	 }
	 
 }