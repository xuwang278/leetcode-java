class Solution {
    // https://www.youtube.com/watch?v=1YQmI7F9dJ0&t=127s
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String ans = "";
        int idx = 0;
        for (char c : strs[0].toCharArray()) {
            for (int i = 1; i < strs.length; i++) {
                String next = strs[i];
                if (idx >= next.length() || c != next.charAt(idx))
                    return ans;
            }
            ans += c;
            idx++;
        }
        return ans;
    }

	// Time: O(n);
	// space: O(1);
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
        	
        	// until res is prefix of strs[i] (or "")
        	while (strs[i].indexOf(res) != 0) {
        		// shrink res from end until ""
        		res = res.substring(0, res.length() - 1);
        	}
        }
        
        return res;
    }

}