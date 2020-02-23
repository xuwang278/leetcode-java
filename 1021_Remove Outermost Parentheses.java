class Solution {
    public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int l = 0; 
        for (char c : S.toCharArray()) {
            if (c == '(') {
                if (l != 0) sb.append(c); // 只要不是第一个(
                l++;
            } else if (c == ')') {
                if (l != 1) sb.append(c); // 只要不是最后一个)
                l--;
            }
        }
        return sb.toString();
    }


	// 寻找一段合法的()串, 删掉最左(和最右), 放入sb中
    // Time: O(n)
	// Space: O(n)
    public String removeOuterParentheses(String S) {
    	StringBuilder sb = new StringBuilder();
    	int cnt = 0, start = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if (S.charAt(i) == '(') cnt++;
    		else cnt--;
    		if (cnt == 0) {
    			sb.append(S.substring(start + 1, i));
    			start = i + 1;
    		}
    	}
    	return sb.toString();
    }
    
	// opened count the number of opened parenthesis.
	// Add every char to the result,
	// unless the first left parenthesis,
	// and the last right parenthesis.

	// Time: O(n)
	// Space: O(n)
    public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int l = 0;
        for (char c : S.toCharArray()) {
        	if (c == '(' && l++ > 0) sb.append(c);
        	if (c == ')' && l-- > 1) sb.append(c);
        }
        return sb.toString();
    }


}