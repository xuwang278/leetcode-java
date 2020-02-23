class Solution {

	private int[] nums;
	//private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    public int[] reset() {
        return nums;
    }
    
    // Knuth's Shuffling
    public int[] shuffle() {
    	int[] a = nums.clone();
        int n = a.length;
        for (int i = 0; i < n; i++) {
        	int r = i + (int) (Math.random() * (n - i)); // // between i and n-1
        	// int r = random.nextInt(i + 1); // [0, i)
        	exch(a, i, r);
        }
        return a;
    }

    private void exch(int[] nums, int i, int j) {
    	int swap = nums[i];
    	nums[i] = nums[j];
    	nums[j] = swap;
    }

}