class Solution {
    // a more easier to understnd version
    // https://www.youtube.com/watch?v=uFso48YRRao
    // https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int n : nums2) {
            while (!stack.isEmpty() && stack.peek() < n) // n is the next greater item for stack.peek()
                map.put(stack.pop(), n);
            stack.push(n);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++)
            ans[i] = map.getOrDefault(nums1[i], -1);
        return ans;
    }

    // Solution 3: stack
    // T: O(m + n)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    	int[] ans = new int[nums1.length];
    	Stack<Integer> stack = new Stack<>();
    	Map<Integer, Integer> map = new HashMap<>();

    	for (int i = nums2.length - 1; i >= 0; i--) {
    		while (!stack.isEmpty() && nums2[i] > stack.peek()) {
    			stack.pop();
    		}

    		if (stack.isEmpty()) {
    			map.put(nums2[i], -1);
    		} else {
    			map.put(nums2[i], stack.peek());
    		}

    		stack.push(nums2[i]);
    	}

    	for (int i = 0; i < nums1.length; i++) {
    		ans[i] = map.get(nums1[i]);
    	}
    	return ans;
    }

    


}