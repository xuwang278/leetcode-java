class Solution {
	public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // current max
            if (max < sum) max = sum; // update max
            if (sum < 0) sum = 0; // no way to get larger since sum < 0, set it to 0 to re-accumulate
        }
        return max;
    }

    // DP
    // T: O(n)
    // S: O(n)
    // see 53.pptx
    public int maxSubArray(int[] nums) {
    	int[] dp = new int[nums.length]; // no padding
    	dp[0] = nums[0]; 
    	int max = dp[0];
    	for (int i = 1; i < nums.length; i++) {
    		dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]); 
    		max = Math.max(max, dp[i]);
    	}
    	return max;
	}
	
	// DP (optimized)
    // T: O(n)
    // S: O(1)
	public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int n : nums) {
            sum = Math.max(sum + n, n); // 累加更大的sum, 如果变小, 就以当前数为起点, 重新寻找subarray
            max = Math.max(max, sum);
        }
        return max;
    }


	// Divide and conquer I
    // T: O(nlogn)
    // S: O(nlogn)
	public int maxSubArray(int[] nums) {
		return maxSubArray(nums, 0, nums.length - 1);
	}

	private int maxSubArray(int[] nums, int lo, int hi) {
		// divide by the pattern [lo, mid] and [mid+1, hi],
		// the case of lo > hi never happens
		if (lo == hi) return nums[lo];
		
		int mid = lo + (hi - lo) / 2;
		int leftSum = maxSubArray(nums, lo, mid);
		int rightSum = maxSubArray(nums, mid + 1, hi);
		int crossSum = cross(nums, lo, mid, hi); // cross mid
		// return the largest of three
		return Math.max(crossSum, Math.max(leftSum, rightSum));
	}

	private int cross(int[] nums, int lo, int mid, int hi) {
		int leftSum = Integer.MIN_VALUE; // negative counts (nums[mid])
		int sum = 0;

		// important to iterate from mid to lo
		// Ex [-2, 1, -3], lo = 0, mid = 1, hi = 2
		// leftSum = -1 if interate from lo to mid
		// but should be 1
		for (int i = mid; i >= lo; i--) {
			sum += nums[i];
			leftSum = Math.max(leftSum, sum);
		}
		// at this moment, leftSum may be < 0
		// since it is initialized to be -infinite and
		// the for loop will run anyway

		int rightSum = Integer.MIN_VALUE; // (nums[mid + 1])
		sum = 0;
		for (int i = mid + 1; i <= hi; i++) {
			sum += nums[i];
			rightSum = Math.max(rightSum, sum);
		}

		// combine the two max from two sides 
		return leftSum + rightSum;
	}

	// Divide and conquer II
    // T: O(nlogn)
    // S: O(nlogn)
	public int maxSubArray(int[] nums) {
		return maxSubArray(nums, 0, nums.length - 1);
	}

	private int maxSubArray(int[] nums, int lo, int hi) {
		if (lo == hi) return nums[lo];
		if (lo > hi) return Integer.MIN_VALUE; // hi out of boundary, do not consider

		int mid = lo + (hi - lo) / 2;
		int leftMax = maxSubArray(nums, lo, mid - 1);
		int rightMax = maxSubArray(nums, mid + 1, hi);
		int crossMax = cross(nums, lo, mid, hi); // this statement also can be listed before leftMAx, between leftMax and rightMax
		return Math.max(crossMax, Math.max(leftMax, rightMax));
	}

	private int cross(int[] nums, int lo, int mid, int hi) {
		int leftMax = 0; 
		int sum = 0;
		for (int i = mid - 1; i >= lo; i--) { 
			sum += nums[i];
			leftMax = Math.max(sum, leftMax); 
		}
		
		int rightMax = 0;
		sum = 0;
		for (int i = mid + 1; i <= hi; i++) {
			sum += nums[i];
			rightMax = Math.max(sum, rightMax);
		}
		// negative doesn't count: return nums[mid] if either leftMax or rightMax is negative
		return leftMax + nums[mid] + rightMax; // leftMax and rightMax are initialized to be 0
	}

	// https://www.youtube.com/watch?v=kekmCQXYwQ0
    // T: O(n)
    // S: O(1)
    public int maxSubArray(int[] nums) {
    	int max_so_far = nums[0];
    	int max_ending_here = 0;
    	for (int i = 0; i < nums.length; i++) {
    		max_ending_here = max_ending_here + nums[i];

    		if (max_so_far < max_ending_here) {
    			max_so_far = max_ending_here;
    		}

    		// no hopes to get such subarray starting from s
    		// because encountering negative component
    		if (max_ending_here < 0) {
    			max_ending_here = 0;
    		}
    	}
    	return  max_so_far;
    }

    // find such subarray
    // https://www.youtube.com/watch?v=kekmCQXYwQ0
    public int[] maxSubArray(int[] nums) {
    	int max_so_far = nums[0];
    	int max_ending_here = 0;

    	int start = 0, end = 0; // start and end of subarray
    	int s = 0; // possible starting for candidate subarrays
    	
    	for (int i = 0; i < nums.length; i++) {
    		max_ending_here = max_ending_here + nums[i];

    		if (max_so_far < max_ending_here) {
    			max_so_far = max_ending_here;
    			
    			start = s;
    			end = i;
    		}

    		// no hopes to get such subarray starting from s
    		// because encountering negative component
    		// Thus, extending search from i + 1
    		if (max_ending_here < 0) {
    			max_ending_here = 0;
    			
    			s = i + 1;
    		}
    	}

    	return Arrays.copyOfRange(nums, start, end + 1);
    }

}
