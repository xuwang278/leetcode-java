class Solution {
    // Sol 1: one map
    // Time: O(len(s))
    // Space: O(len(t))
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        int lo = 0, hi = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        int cnt = 0;
        
        for (; hi < s.length(); hi++) {
            char r = s.charAt(hi);
            if (map.containsKey(r)) {
                map.put(r, map.get(r) - 1);
                if (map.get(r) == 0) cnt++;
            }
            

            for (; cnt == map.size(); lo++) {
                // hi points to the char up to which all chars in t are found in s from lo to hi, inclusively
                if (hi - lo + 1 < minLen) {
                    minLen = hi - lo + 1;
                    start = lo;
                }
                
                char l = s.charAt(lo);
                if (map.containsKey(l)) {
                    map.put(l, map.get(l) + 1);
                    if (map.get(l) == 1) cnt--;
                }
            }
            
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
        
    }

    

    // Detail:
	public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 
            || s.length() < t.length())
            return "";
        
        Map<Character, Integer> map = buildMap(t); // char -> freq
        
        int lo = 0, hi = 0; // two pointers
        int start = 0, minLen = Integer.MAX_VALUE;
        int matchCount = 0; // # of chars of t found in s
        while (hi < s.length()) {
            // expand hi until condition satisfies: matchCount == map.size()
            char r = s.charAt(hi);
            if (map.containsKey(r)) {
                map.put(r, map.get(r) - 1); 
                if (map.get(r) == 0) matchCount++; // 由正变零，cnt++ (变负无所谓，只说明有多余的r，但r是满足最低个数的，所以不用update MmatchCount)
            }
            hi++;
            
            // a valid hi has reached
            // 当满足条件后，尝试移动slow，减小窗口宽度，看是否条件仍满足
            // 这样，得到以(fast-1)为结尾的最小的局域解
            while (matchCount == map.size()) {
                // update minLen and start
                if (hi - lo < minLen) {
                    minLen = hi - lo;
                    start = lo;
                }
                
                // contract lo until condition dissatisfies: 
                // matchCount != map.size()
                char l = s.charAt(lo);
                if (map.containsKey(l)) {
                    map.put(l, map.get(l) + 1);
                    if (map.get(l) == 1) matchCount--; // 由零变正，cnt-- (一旦cnt--，立刻退出loop)
                }
                lo++;
            }
            // best local minLen is achieved which ends with (hi - 1)
            
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
    
    private Map<Character, Integer> buildMap(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        return map;
    }

    // Sol 2: two maps

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> curMap = new HashMap<>();

    }
	
}
