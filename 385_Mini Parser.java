class Solution {

	// Solution 1: Iterative
	private Stack<NestedInteger> stack = new Stack<>();
	private NestedInteger root = null;
	private int num = 0, sign = 1;
	private boolean hasNum = false;

    public NestedInteger deserialize(String s) {
    	for (char c : s.toCharArray()) {
    		if (Character.isDigit(c)) {
    			num = num * 10 + (c - '0');
    			hasNum = true;
    		} else if (c == '-') sign = -1;
    		else if (c == ',') addNumber();
    		else if (c == '[') {
    			stack.push(root);
    			root = new NestedInteger();
    		} else if (c == ']') {
    			addNumber();
    			NestedInteger parent = stack.pop();
    			if (parent !-= null) {
    				parent.add(root);
    				root = parent;
    			}
    		}
    	}
    	addNumber();
    	return root;
    }

    private void addNumber() {
    	if (hasNum) {
    		num = sign * num;
    		sign = 1;
    		if (root == null) root = new NestInteger(num);
    		else root.add(new NestInteger(num));
    		num = 0;
    		hasNum = false;
    	}
    }

    // Solution 2: Recursion
    public NestedInteger deserialize(String s) {
    	NestedInteger res = new NestedInteger();
    	if (s == null || s.length() == 0) return res;

    	if (s.charAt(0) != '[') res.setInteger(Integer.parseInt(s));

    	else if (s.length() > 2) {
    		int start = 1, count = 0;
    		for (int i = 1; i < s.length(); i++) {
    			char c = s.charAt(i);
    			if (count == 0 && (c == ',' || i == s.length() - 1)) {
    				res.add(deserialize(s.substring(start, i)));
    				start = i + 1;
    			} else if (c == '[') count++;
    			else if (c == ']') count--;

    		}
    	}
    	return res;
    }

}


