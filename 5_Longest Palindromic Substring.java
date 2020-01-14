class Solution {
    // Time: O(n^2)
	// Space: O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // dp[i][j]: is substring[i, j] a palindrome
        int lo = 0, hi = 0;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                // "axyyxa" "aba" "aa"
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i > hi - lo) {
                    lo = i;
                    hi = j;   
                }
            }
        }
        return s.substring(lo, hi + 1);
    }

    // Solution 2:
    // Time: O(n^2)
    // Space: O(1)
    int left = 0, maxLen = 0;
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return s;
        if (s.length() < 2) return s;

        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i); //assume odd length, try to extend Palindrome as possible
            expand(s, i, i + 1); //assume even length
        }
        return s.substring(left, left + maxLen);
    }

    private void expand(String s, int lo, int hi) {
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
            lo--;
            hi++;
        }

        if (maxLen < hi - lo - 1) {
            left = lo + 1;
            maxLen =  hi - lo - 1;
        }
    }
}