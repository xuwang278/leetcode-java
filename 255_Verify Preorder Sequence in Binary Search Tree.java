class Solution {
	// https://www.youtube.com/watch?v=_k944o5yilQ
	// http://www.cnblogs.com/grandyang/p/5327635.html
	public boolean verifyPreorder(int[] preorder) {
		Stack<Integer> stack = new Stack<>();
		int min = Integer.MIN_VALUE;
		for (int num : preorder) {
			if (num < min) return false;
			while (!stack.isEmpty() && num > stack.peek())
				min = stack.pop();
			stack.push(num);
		}
		return true;
	}

	// Solution 2:
	// leetcode p98
	public boolean verifyPreorder(int[] preorder) {
		return verifyPreorder(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean verifyPreorder(int[] preorder, int lo, int hi, int min, int max) {
		if (lo > hi) return true;

		// check if current node is valid
		int val = preorder[lo];
		if (val <= min || val >= max) return false;

		// find right child
		int i = lo + 1;
		while (i <= hi) {
			if (preorder[i] >= val) break;
			i++;
		}
		
		return verifyPreorder(preorder, lo + 1, i - 1, min, val) 
				&& verifyPreorder(preorder, i, hi, val, max);
	}

}