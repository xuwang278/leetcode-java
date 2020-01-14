class Solution {
    // Solution 1: Brute Force
    // T: O(n^3), time limit exceeded
    // S: O(1)
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (sumSubArray(nums, i, j) >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private int sumSubArray(int[] nums, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) sum += nums[k];
        return sum;
    }

    // Solution 2: Brute Force (optimized)
    // T: O(n^2)
    // S: O(n)
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = Integer.MAX_VALUE;

        // sums[i]: nums[0] + ... + nums[i]
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            // linear search
            for (int j = i; j < nums.length; j++) {
                if (sums[j] - sums[i] + nums[i] >= s) {
                    res = Math.min(res, j - i + 1);
                    // res is the current min because j++ always;
                    // no need to continue
                    break; 
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // Solution 3: Binary Search
    // T: O(nlogn)
    // S: O(n)
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = Integer.MAX_VALUE;

        // sums[i]: sum of first i items
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            // prerequsite: sums is an increasing array
            // find min valid j using binary search to speed up
            int j = findWindowEnd(sums, i, s) - 1; // padding in sums
            if (j == -2) break; // no valid j is found
            res = Math.min(res, j - i + 1);
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
    
    // find the min j index such as sum of subarray nums[i,j] >= target
    private int findWindowEnd(int[] sums, int start, int target) {
        int lo = start + 1, hi = sums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            int sum = sums[mid] - sums[start];
            if (sum >= target) hi = mid; 
            else lo = mid;
        }
        if (sums[lo] - sums[start] >= target) return lo;
        if (sums[hi] - sums[start] >= target) return hi;
        return -1; // no valid window is found
    }

    // Solution 5: Two Pointers, Sliding Window
    // T: O(n)?
    // S: O(1)
    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // find all valid subaray[left, i] and update res
            while (left <= i && sum >= s) {
                res = Math.min(res, i - left + 1);
                // min size of subarray starting from left is achieved and stored in res
                // leaving left and increment i will only produce larger size
                // so left can increment now
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
        
}

