class Solution {
	// TLE
	// T: O(n^3)
    public int longestSubstring(String s, int k) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isValid(sub, k)) max = Math.max(max, sub.length());
            }
        }
        return max;
    }
    
    private boolean isValid(String s, int k) {
        Map<Character, Integer> charToFreq = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!charToFreq.containsKey(c)) charToFreq.put(c, 1);
            else charToFreq.put(c, charToFreq.get(c) + 1);
        }
        
        for (int freq : charToFreq.values())
            if (freq < k) return false;
        return true;
    }

    // Solution 2: two pointers
    // https://www.youtube.com/watch?v=GU-03VY12Ic
    // T: O(n)
    // S: O(1)
    public int longestSubstring(String s, int k) {
    	// base case 1: lo > s.length() or lo == hi
    	if (s == null || s.length() == 0) return 0;

    	// set-up (recount freq for each substring)
    	int[] hash = new int[26];
    	for (int i = 0; i < s.length(); i++) {
    		hash[s.charAt(i) - 'a']++;
    	}

    	// base case 2
    	boolean fullString = true;
    	for (int i = 0; i < s.length(); i++) {
    		if (hash[s.charAt(i) - 'a'] > 0 && hash[s.charAt(i) - 'a'] < k)
    			fullString = false;
    	}
    	if (fullString) return s.length(); // s is valid

    	// all the infrequent elements are used as splits
    	int lo = 0, hi = 0, res = 0;
    	while (hi < s.length()) {
    		if (hash[s.charAt(hi) - 'a'] < k) {
    			res = Math.max(res, longestSubstring(s.substring(lo, hi), k));
    			lo = hi + 1;
    		}
    		hi++;
    	}

    	res = Math.max(res, longestSubstring(s.substring(lo), k));
    	return res;
    }

// test case: ababacb (set-up is necessary for each substring)










}