class Solution {
	// T: O(n)
	// S: O(n)
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;

        int n = nums.length;
        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            aux[(i + k) % n] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = aux[i];
        }
    }

    // T: O(n)
	// S: O(1)
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        k = k % nums.length;
        rotate(nums, 0, nums.length - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, nums.length - 1);
    }

    private void rotate(int[] nums, int lo, int hi) {
    	while (lo < hi) swap(nums, lo++, hi--);
    }

    private void swap(int[] nums, int lo, int hi) {
    	int temp = nums[lo];
    	nums[lo] = nums[hi];
    	nums[hi] = temp;
    }
}

original:
      k
1 2 3 4 5 6 7 


swap the whole:
      k 
7 6 5 4 3 2 1


swap [0, k - 1]:
      k 
5 6 7 4 3 2 1


swap [k, nums.length - 1]:
      k 
5 6 7 1 2 3 4



