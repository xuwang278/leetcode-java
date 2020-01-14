class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int leftMost = -1;
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == '(') stack.push(i);
        	else {
        		if (stack.isEmpty()) leftMost = i;
        		else {
        			stack.pop(); // pop临近的(, 剩下较远的(, 这样max最大
        			if (stack.isEmpty()) max = Math.max(max, i - leftMost);
        			else max = Math.max(max, i - stack.peek());
        		}
        	}
        }
        return max;
    }
}


