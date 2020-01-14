class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int max = 0;
        Set<Character> set = new HashSet<>();
        int lo = 0, hi = 0;
        while (hi < s.length()) {
            char r = s.charAt(hi);
            if (!set.contains(r)) {
                set.add(r);
                hi++;
                max = Math.max(max, hi - lo); // when it's a valid substring, update max
            } else {
                // when it's not valid, remove charAt lo to resolve
                char l = s.charAt(lo);
                set.remove(l);
                lo++;
            }
        }
        return max;
    }

    // template as LC 438
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>(); 

        int slow = 0,fast = 0, max = 0;
        int matchCount = 0; // # of duplicates
        while (fast < s.length()) {
            char c = s.charAt(fast);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
                if (map.get(c) > 1) matchCount++;
            } else {
                map.put(c, 1);
            }
            fast++;
            
            while (matchCount > 0) {
                char leftMost = s.charAt(slow);
                if (map.containsKey(leftMost)) {
                    map.put(leftMost, map.get(leftMost) - 1);
                    if (map.get(leftMost) == 1) matchCount--;
                }
                slow++;
            }
            
            max = Math.max(max, fast - slow);

        }
        return max;
    }


	// Time: O(n)
    // Space: O(1)
    // Shifting Window
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] map = new int[128];
        int max = 0;
        int lo = 0, hi = 0;
        while (hi < s.length()) {
            if (map[s.charAt(hi)] != 0) {
                map[s.charAt(lo++)]--;
            } else {
                map[s.charAt(hi++)]++;
                max = Math.max(max, hi - lo);
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>(); // store substring candidates
        int start = 0, end = 0, max = 0;
        while (end < s.length()) {
        	if (set.contains(s.charAt(end))) {
        		set.remove(s.charAt(start++));
        	} else {
        		set.add(s.charAt(end++));
        		max = Math.max(max, end - start); // end has been advanced at this moment
        	}
        }
        return max;
    }

    


}