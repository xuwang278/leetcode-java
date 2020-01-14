class Solution {
    // Time: O(n)
    // Space: O(n)
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false; // no left-para to couple with
                else {
                    char l = stack.pop();
                    if (l == '(' && c != ')') return false;
                    if (l == '[' && c != ']') return false;
                    if (l == '{' && c != '}') return false;
                }
            }
        }
        
        return stack.isEmpty(); // check if there is any uncoupled left-para
    }

    
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(')');
            else if (s.charAt(i) == '[') stack.push(']');
            else if (s.charAt(i) == '{') stack.push('}');

            // 1st condition: "}"
            // 2nd condition: "[}"
            else if (stack.isEmpty() || stack.pop() != s.charAt(i))
            	return false;
        }
        return stack.isEmpty();
    }
}