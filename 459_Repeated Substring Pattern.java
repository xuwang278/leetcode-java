class Solution {
	// https://www.cnblogs.com/grandyang/p/6087347.html
    public boolean repeatedSubstringPattern(String s) {
        int size = s.length();
        for (int i = size / 2; i >= 1; i--) {
        	if (size % i != 0) continue;
        	// build new string based on possible monomer identified
        	int repeat_num = size / i;
        	String monomer = s.substring(0, i);
        	String str = "";
        	for (int j = 0; j < repeat_num; j++)
        		str += monomer;
        	if (str.equals(s)) return true;
        }
        return false;
    }

    public boolean repeatedSubstringPattern(String s) {
        // "abcabc"的dp数组为[0 0 0 0 1 2 3]
        // 最后一个位置数字为3，就表示重复的字符串的字符数有3个
        // 最后一个数字不能为0，而且还要满足dp[n] % (n - dp[n]) == 0
        int i = 1, j = 0, n = s.length();
        int[] dp = new int[n + 1];
        while (i < n) {
        	if (s.charAt(i) == s.charAt(j)) dp[++i] = ++j;
        	else if (j == 0) ++i;
        	else j = dp[j];
        }
        return dp[n] != 0 && (dp[n] % (n - dp[n]) == 0); // n - dp[n]是一个子字符串的长度
        
    }

    public boolean repeatedSubstringPattern(String str) {
		String s = str + str;
		return s.substring(1, s.length() - 1).contains(str);
	}
}


