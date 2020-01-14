class Solution {
	// T: O(n)
	// S: O(n)
	// Compare String objects by equals()
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String str : tokens) {
        	if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
        		int right = Integer.parseInt(stack.pop());
        		int left  = Integer.parseInt(stack.pop());
        		int res = 0;
        		if (str.equals("+") ) {
        			res = left + right;
        		} else if (str.equals("-")) {
        			res = left - right;
        		} else if (str.equals("*")) {
        			res = left * right;
        		} else if (str.equals("/") ) {
 					res = left / right;
        		}
        		String resStr = Integer.toString(res);
        		stack.push(resStr);
        	} else {
        		stack.push(str);
        	}
        }
        return Integer.parseInt(stack.pop());
    }
}