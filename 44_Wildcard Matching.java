class Solution {
    // https://www.youtube.com/watch?v=3ZDZ-N0EPV0
    // Time: O(mn)
	// Space: O(mn)
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
    	char[] text = s.toCharArray();
    	char[] pattern = p.toCharArray();

    	// dp[i][j] == true: text前i个字符与pattern前j个字符match
    	boolean[][] dp = new boolean[m + 1][n + 1]; 
        dp[0][0] = true;

        // deals with patterns like * or **, *** that match w/ empty string
        for (int i = 1; i <= n; i++) {
        	if (pattern[i - 1] == '*') {
        		dp[0][i] = dp[0][i - 1];
        	}
        }

        for (int i = 1; i <= m; i++) {
        	for (int j = 1; j <= n; j++) {
        		// 当前两个字符相同, 这两个字符必然match, 忽略这两个字符, 看剩余是否匹配, 即, 继承左上方
        		if (pattern[j - 1] == '?' || pattern[j - 1] == text[i - 1]) {
        			dp[i][j] = dp[i- 1][j - 1];
        		} 
        		// pattern忽略*, 与原text是否匹配, 或者
        		// text忽略当前字符, 与原pattern是否匹配
        		else if (pattern[j - 1] == '*') {
        			dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        		}
        	}
        }
        
        return dp[m][n];
    }
}
