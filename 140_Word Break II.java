class Solution {
    // P140 (adopted from P139)
    // Recursion with memorization (DP) 
    // T: O(2^n)
    // S: O(2^n)
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> dp = new HashMap<>();
        return dfs(s, wordDict, dp);
    }

    private List<String> dfs(String s, List<String> wordDict,  Map<String, List<String>> dp) {
        if (dp.containsKey(s)) return dp.get(s); // encounter before
        List<String> res = new ArrayList<>();
        // if (s.length() == 0) return res; // empty string;
        // s is self word break
        if (wordDict.contains(s)) {
            res.add(s);
            //return res; // its substring may also in wordDict
        }
        
        // divide s to every possible substring
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            if (!wordDict.contains(right)) continue;
            // if right is in wordDict, recursive call on left
            List<String> subList = dfs(left, wordDict, dp);
            // if subList is empty, for loop will not run
            for (String str : subList) {
                res.add(str + " " + right); // combine current right with previous list
            }
        }
        dp.put(s, res);
        return res;
    }
    
    // Memory Limit Exceeded
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String>[] dp_list = (List<String>[]) new List[s.length() + 1];
		for (int i = 0; i < dp_list.length; i++) {
            dp_list[i] = new ArrayList<>();
        }
        boolean[] dp = new boolean[s.length() + 1]; 
        dp[0] = true; // "" is in wordDict
        
        //dp[0] = {};
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    if (dp_list[j].size() == 0) 
                        dp_list[i].add(s.substring(j, i));

                    for (String str : dp_list[j]) {
                        dp_list[i].add(str + " " + s.substring(j, i));
                    }
        
                }
            }
        }
		
		return dp_list[s.length()];
    }


}