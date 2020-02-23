class NumArray {
    private int[] nums; // store the immutable array

    // O(1)
    public NumArray(int[] nums) {
        this.nums = nums;
    }
    
    // O(n)
    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) sum += nums[k];
        return sum;
    }
}

// array is not change and SumRange can be called many times during usage/testing,
// so it is necessary to make SumRange work as O(1).
class NumArray {
    private int[] sum; // sum[i] - sum of first i items

    // O(n)
    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }
    
    // O(1)
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}

nums:
    1   3
  1 2 3 4 5

sum:
  1     4
0 1 3 6 10 15