class Solution {
	// https://www.youtube.com/watch?v=l3hda49XcDE
	// Time: O(mn)
	// Space: O(mn)
    public boolean isMatch(String s, String p) {
    	int m = s.length(), n = p.length();
    	char[] text = s.toCharArray();
    	char[] pattern = p.toCharArray();

    	// dp[i][j] == true: text前i个字符与pattern前j个字符match
        boolean[][] dp = new boolean[m + 1][n + 1]; 
        dp[0][0] = true;

        // deals with patterns like a* or a*b* that match w/ empty string
        for (int i = 1; i <= n; i++) {
        	if (pattern[i - 1] == '*') {
        		dp[0][i] = dp[0][i - 2]; // 忽略*和它之前的字符, 看剩余是否匹配
        	}
        }

        for (int i = 1; i <= m; i++) {
        	for (int j = 1; j <= n; j++) {
        		// 当前两个字符相同, 这两个字符必然match, 忽略这两个字符, 看剩余是否匹配, 即, 继承左上方
        		if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
        			dp[i][j] = dp[i - 1][j - 1]; 
        		} else if (pattern[j - 1] == '*') {
        			// (1) 不使用*和它之前的字符, 使用相同的text, 是否match, 即, 继承左左方
        			dp[i][j] = dp[i][j - 2]; 
        			// (2) 再看*之前的字符是否与text中当前字符相同
        			// 如果相同, 忽略text当前字符 (该字符已经确定可以被pattern表示了), pattern不变
        			// 看剩余的text是否可以被相同pattern匹配, 即, 继承正上方
        			if (pattern[j - 2] == '.' || pattern[j - 2] == text[i - 1]) {
        				dp[i][j] = dp[i][j] || dp[i - 1][j]; 
        			}
        		} 
        		// 当前两个字符不同, 不可能match
        		else dp[i][j] = false;
        	}
        }

        return dp[m][n];
    }

}

