class Solution {
    // T: O(n^2)
    // S: O(n)
    public int lengthOfLIS(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	// maxLenEndedBy[i] stores the length of the longest sequence ended with nums[i]
    	int[] maxLenEndedBy = new int[nums.length];

    	// initialize each entry as 1 because the length of subsequence 
    	// ended with any one of char in nums[] is at least 1
    	int maxLen = 0;

    	Arrays.fill(maxLenEndedBy, 1); 
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = 0; j < i; j++) {

    			// find an increasing "sequence" regardless of if or not they are continuous 
    			if (nums[i] > nums[j]) { 

    				//                       j   i
    				// nums =          {3,4,-1,0,6,2,3}
    				// maxLenEndedBy = {1,2, 1,2,3,1,1}

    				// when i is at 6, j is at -1, no need to update maxLenEndedBy[i] because 3 > 1+1, 
    				// i.e. the longest subsequence ended with 6 can be achieved from 3,4,6, 
    				// instead of -1, 6
    				maxLenEndedBy[i] = Math.max(maxLenEndedBy[j] + 1, maxLenEndedBy[i]);
    			}
    		}

    		// update maxLen after finishing visit on each i of maxLenEndedBy[]
    		maxLen = Math.max(maxLen, maxLenEndedBy[i]);
    	}

    	return maxLen;
    }

    // Invariant: 
    // For a length i (fromt 1 to nums.length), there may be multiple increasing 
    // subsequence, among which smallestTailWithLength[i] stores the value that 
    // is the smallest last item (tail) for the sequence.

    // For example, nums = {4, 5, 6, 3}
	// len = 1:  [4], [5], [6], [3]   => smallestTailWithLength[1] = 3
    // len = 2:  [4, 5], [5, 6]       => smallestTailWithLength[2] = 5 (since 5 < 6)
	// len = 3:  [4, 5, 6]            => smallestTailWithLength[3] = 6

    // The smallestTailWithLength[] is an increasing array, i.e. smallestTailWithLength[i] increases 
    // as i increases, because it represents increasing sequence. 

    // To maintain the smallest tail variant, for each num in nums[], a binary search is performed to find 
    // the ceil of num (i.e. smallestTailWithLength[ceil] > num) 
    // ceil can be larger than size of smallestTailWithLength[], which means a longer increasing subsequence 
    // extended by one is found, or smaller than size, which means a smaller tail is found.

	// Update size of smallestTailWithLength[] and return as result.

    // For example,
    // nums =                   {10, 9, 2, 5, 3, 7, 101, 18}
    // smallestTailWithLength = {0, 2, 3, 7, 18} 
    // longest increasing subsequence: 2, 3, 7, 18

    // T: O(nlgn)
    // S: O(n)
	public int lengthOfLIS(int[] nums) {
        int[] smallestTailWithLength = new int[nums.length + 1];
        int size = 0;
        for (int num : nums) {
            int ceil = ceil(smallestTailWithLength, size, num);
            size = Math.max(size, ceil); // if ceil > size, size++;
            smallestTailWithLength[ceil] = num; // replace or append
        }
        return size;
    }

    private int ceil(int[] array, int hi, int target) {
        int lo = 1;
        while (lo <= hi) {
            int mid = lo + (hi-lo) / 2;
            if (array[mid] < target) lo = mid + 1; 
            else if (array[mid] > target) hi = mid - 1; 
            else return mid; // hit
        }
        return lo; // find ceil of target
        // return -1 for regular binary search
        // return right to find floor of target
    }

    // using Java TreSeet
    public class Solution {
	    public int lengthOfLIS(int[] nums) {
	        TreeSet<Integer> set = new TreeSet<Integer>();
	        for(int num : nums) {
	            Integer ceil = set.ceiling(num);
	            if(ceil != null) set.remove(ceil);
	            set.add(num);
	        }
	        return set.size();
	    }
	}


}


