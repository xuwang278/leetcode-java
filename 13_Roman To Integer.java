class Solution {
	// Time:O(n)
	// Space:O(1)
	public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        char[] chs = s.toCharArray();
        int ans = charToInt(chs[0]);
        for (int i = 1; i < chs.length; i++) {
            int cur = charToInt(chs[i]);
            int pre = charToInt(chs[i - 1]);
            if (cur > pre) {
                ans += cur - 2 * pre;
            } else {
                ans += cur;
            }
        }
        
        return ans;
    }
    
    private int charToInt(char c) {
		if (c == 'I') return 1;
		if (c == 'V') return 5;
		if (c == 'X') return 10;
		if (c == 'L') return 50;
		if (c == 'C') return 100;
		if (c == 'D') return 500;
		if (c == 'M') return 1000;
		else return 0;
	}

}