class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return 0;
        
        Map<Character, Integer> map = new HashMap<>(); // char -> freq
        int lo = 0, hi = 0, max = Integer.MIN_VALUE;
        while (hi < s.length()) {
            // loading chars to map
            char r = s.charAt(hi);
            map.put(r, map.getOrDefault(r, 0) + 1);
            hi++;
            
            // resolve: only move lo when it has to do
            while (map.size() > k) {
                char l = s.charAt(lo);
                map.put(l, map.get(l) - 1);
                if (map.get(l) == 0) map.remove(l);
                lo++;
            }
            
            // get a valid answer
            max = Math.max(max, hi - lo);
        }
        
        return max;
    }
}
    
        // 错误的, 下面的代码求的是只有K个不同字符的最长串
        // 最长串也可以是小于K个字符
        while (hi < s.length()) {
            char r = s.charAt(hi);
            map.put(r, map.getOrDefault(r, 0) + 1);
            hi++;
            
            while (map.size() == k) {
                max = Math.max(max, hi - lo);
                char l = s.charAt(lo);
                map.put(l, map.get(l) - 1);
                if (map.get(l) == 0) map.remove(l);
                lo++;
            }
            
        }