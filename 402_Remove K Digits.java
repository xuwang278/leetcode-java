class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (n == k) return "0";

        // 若有递减，删除大数
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
        	while (!stack.isEmpty() && stack.peek() > num.charAt(i) && k > 0) {
        		stack.pop();
        		k--;
        	}
        	stack.push(num.charAt(i));
        }

        // 若无递减，删除末尾
        while (k > 0) {
        	stack.pop();
        	k--;
        }

        // construct string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        
        // while(!stack.isEmpty()) sb.append(stack.pop());
        // sb.reverse();

        // delete leading 0
        while (sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.toString();
    }
}