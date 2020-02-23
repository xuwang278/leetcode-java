class Solution {
	// Solution 1: Stack
    // https://www.programcreek.com/2015/01/leetcode-verify-preorder-serialization-of-a-binary-tree-java/
	// T: O(n)
	// S: O(n)
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) return false;

    	Stack<String> stack = new Stack<>();
    	String[] strs = preorder.split(",");
    	for (String str : strs) {
    		if (str.equals("#")) {
    			while (!stack.isEmpty() && stack.peek().equals("#")) {
    				stack.pop();
    				if (stack.isEmpty()) return false;
    				stack.pop();
    			}
    		}
    		stack.push(str);
    	}
    	return stack.size() == 1 && stack.peek().equals("#");
    }

    // Solution 1: in/out - degree
    // T: O(n)
    // S: O(1)
    public boolean isValidSerialization(String preorder) {
	    String[] nodes = preorder.split(",");
	    int diff = 1;
	    for (String node: nodes) {
	        if (--diff < 0) return false;
	        if (!node.equals("#")) diff += 2;
	    }
	    return diff == 0;
	}
    
}