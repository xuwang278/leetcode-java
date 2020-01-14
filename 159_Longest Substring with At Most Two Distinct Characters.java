class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int lo = 0, hi = 0;
        int cnt = 0, start = 0, maxLen = 0;
        while (hi < s.length()) {
            char r = s.charAt(hi);
            map.put(r, map.getOrDefault(r, 0) + 1);
            if (map.get(r) == 1) cnt++;
            hi++;

            while (cnt > 2) {
                char l = s.charAt(lo);
                map.put(l, map.get(l) - 1);
                if (map.get(l) == 0) cnt--;
                lo++;
            }
            
            // cnt <= k
            maxLen = Math.max(maxLen, hi - lo);
        }
        
        return maxLen;
    }
}