class Solution {
    // Recursion with memorization (DP) 记忆化递归
    // T: O(n^2)
    // S: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> map = new HashMap<>(); // string -> decompositable?
        return dfs(s, dict, map);
    }
    
    private boolean dfs(String s, Set<String> dict, Map<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s); // met before
        
        // s = "" + s
        if (dict.contains(s)) {
            map.put(s, true);
            return true;
        }
        
        for (int i = 1; i < s.length(); i++) {
            String l = s.substring(0, i);
            String r = s.substring(i);
            if (!dict.contains(r)) continue; // l + r is not a breaking way
            if (dfs(l, dict, map)) {
                map.put(s, true); // s = l + r
                return true;
            }
        }
        
        map.put(s, false); // s can not be broken
        return false;
    }

    // DP
    // T: O(n^2 - n^3) (nested loop O(n^2) + substring() O(n))
    // S: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] = true: substring [0, i-1] (with length i) can break to words that are in wordDict
        boolean[] dp = new boolean[s.length() + 1]; 
        dp[0] = true; // "" is in wordDict
        for (int i = 1; i <= s.length(); i++) { // i: length of subproblem
            for (int j = 0; j < i; j++) { // j: break point
                if (!dp[j]) continue;
                if (wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // get a way to break s[i] to s[0,j] + s[0,i], then break the inner loop
                }
            }
        }
        return dp[s.length()];
    }

    // DP 
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean dp[] = new boolean[s.length() + 1]; // can the first i chars break to words in dict?
        dp[0] = true; // "" is in dictionary
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}




