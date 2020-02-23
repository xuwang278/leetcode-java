class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] nums_sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums_sorted);
        int i = 0, j = nums.length - 1;
        while (i < nums.length && nums[i] == nums_sorted[i]) i++;
        while (i < j && nums[j] == nums_sorted[j]) j--;
        return j - i + 1;
    }

    // Solution 2: Stack
    public int findUnsortedSubarray(int[] nums) {
    	if (nums == null || nums.length < 2) return 0;

    	Stack<Integer> stack = new Stack<>();
    	int lo = nums.length - 1;
    	int hi = 0;
    	for (int i = 0; i < nums.length; i++) {
    		if (stack.isEmpty() || nums[i] >= nums[stack.peek()]) {
    			stack.push(i); // index
    		} else {
    			while (!stack.isEmpty()  && nums[i] < nums[stack.peek()]) {
    				lo = Math.min(lo, stack.pop());
    			}	
    		}
    	}

    	stack.clear();

    	for (int i = nums.length - 1; i >= 0; i--) {
    		if (stack.isEmpty() || nums[i] <= nums[stack.peek()]) {
    			stack.push(i);
    		} else {
    			while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
    				hi = Math.max(hi, stack.pop());
    			}	
    		}
    	}

    	return hi - lo > 0 ? hi - lo + 1 : 0;
    }
}