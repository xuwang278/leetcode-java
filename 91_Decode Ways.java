class Solution {
    https://www.youtube.com/watch?v=ZLwwc3-vVP4

	// Solution 1: Recursion: read 1st/2nd digit each level
	// T: O(2^n) Binary tree: max # of nodes: 2^n
	// S: O(n) Binary tree: max height = n
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return dfs(s, 0);
    }
    
    private int dfs(String s , int index) {
        if (index == s.length()) return 1; // get a way
        int ways = 0;
        if (s.charAt(index) != '0') ways += dfs(s, index + 1);
        if (isValid(s, index)) ways += dfs(s, index + 2);
        return ways;
    }
    
    // has form a  valid 2-digit # fromt start?
    private boolean isValid(String s, int start) {
        if (start == s.length() - 1) return false;
        if (s.charAt(start) == '1') return true; 
        if (s.charAt(start) == '2' && s.charAt(start + 1) <= '6') return true;
        return false;
    }

    // Solution 2: Recursion with memoization (Top-Down DP)
    // T: O(1) * (n+1) = O(n)
    // S: O(n)
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1]; // dp[i] # of ways for substrung[0, i]
        Arrays.fill(dp, -1);
        return dfs(s, 0, dp);
    }
    
    private int dfs(String s , int index, int[] dp) {
        if (dp[index] != -1) return dp[index];
        if (index == s.length()) {
            dp[index] = 1;
            return 1; // get a way
        }
        int ways = 0;
        if (s.charAt(index) != '0') ways += dfs(s, index + 1, dp);
        if (isValid(s, index)) ways += dfs(s, index + 2, dp);
        dp[index] = ways;
        return ways;
    }
    
    // has form a  valid 2-digit # fromt start?
    private boolean isValid(String s, int start) {
        if (start == s.length() - 1) return false;
        if (s.charAt(start) == '1') return true; 
        if (s.charAt(start) == '2' && s.charAt(start + 1) <= '6') return true;
        return false;
    }

    // Solution 3: Bottom-Up DP
    // T: O(n)
    // S: O(n)
	DP 四要素:
	(1) State: dp[i]: 从s[0]开始, i个数构成的合法的decoding数量
    (2) Initial: dp[0] = 1
    (3) Function:
    	dp[i] = 0 
     	    	+ dp[i-1] if s[i-1] is 1~9
     	   		+ dp[i-2] if s[i-2]s[i-1] is 10~26
    (4) Result: dp[n]，即n个数构成的decoding数量

    public int numDecodings(String s) {
    	if (s == null || s.length() == 0) return 0;
     	int len = s.length();
     	int[] dp = new int[len + 1]; 
     	dp[0] = 1; 
     	dp[1] = s.charAt(0) != '0' ? 1 : 0;

     	for (int i = 2; i <= len; i++) {
     		int first = Integer.valueOf(s.substring(i - 1, i)); // the i-th char in s
     		int second = Integer.valueOf(s.substring(i - 2, i)); // the (i-1) and i-th char in s
     		if (first >= 1 && first <= 9) dp[i] += dp[i - 1]; // 如果当前数有效，dp[i] += dp[i - 1], (把该数并入dp[n-1]解集：解集数量不变，长度加1)
     		if (second >= 10 && second <= 26) dp[i] += dp[i - 2]; // 有新的二位数， dp[i] += dp[i - 2]， 把其并入dp[i-2]解集
     	}
     	return dp[len];
    }

    // Solution 3: Bottom-Up DP
    // T: O(n)
    // S: O(1) 滚动记录
    public int numDecodings(String s) {
		if (s == null || s.length() == 0) return 0;
		int prepre = 1, pre = s.charAt(0) != '0' ? 1: 0;
	   for (int i = 2; i <= s.length(); i++) {
		   int first = Integer.parseInt(s.substring(i - 1, i));
		   int second = Integer.parseInt(s.substring(i - 2, i));
		   int res = 0;
		   if (1 <= first && first <= 9) res += pre;
		   if (10 <= second && second <= 26) res += prepre;
		   prepre = pre;
		   pre = res;
	   }
	   return pre;
    }

}