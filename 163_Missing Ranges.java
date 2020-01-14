class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        long lo = (long) lower;
        long hi = (long) upper;
        int n = nums.length;
        List<String> ans = new ArrayList<>();
        if (n == 0) {
        	add(ans, lo - 1, hi + 1);
        	return ans;
        }

        add(ans, lo - 1, nums[0]);
        for (int i = 1; i < n; i++) {
        	add(ans, nums[i - 1], nums[i]); // exclusive: 
        }
        add(ans, nums[n - 1], hi + 1);

        return ans;
    }

    // find range that is within (lo, hi), exculsive
    // it is designed to be exclusive because the parameters pass in
    // requires to be exclusive
    private void addRange(List<String> ans, long lo, long hi) {
        if (lo >= hi || lo + 1 == hi) return;
        if (lo + 1 == hi - 1) {
            ans.add(String.valueOf(lo + 1));
            return;
        }
        
        ans.add((lo + 1) + "->" + (hi - 1));
    }

}