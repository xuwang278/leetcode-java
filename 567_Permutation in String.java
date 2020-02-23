class Solution {
    // T: O(n + 2m)
    // S: O(n)
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) 
            return false;
        
        Map<Character, Integer> map = new HashMap<>(); // char -> freq 
        for (char c : s1.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        // substring prlboem: find a ocntinuous substring that meets a condition
        // two pointers lo and hi:
        int lo = 0, hi = 0, cnt = 0;
        while (hi < s2.length()) {
            char r = s2.charAt(hi);
            if (map.containsKey(r)) {
                map.put(r, map.get(r) - 1);
                if (map.get(r) == 0) cnt++;
            }
            hi++;
            
            // narrow down window as long as all chars in s1 are still in it;
            // when width of window == s1.length(), a permutation is found;
            // when chars are not in it, move hi again to accumulate more chars
            while (cnt == map.size()) {
                if (hi - lo == s1.length()) return true;
                char l = s2.charAt(lo);
                if (map.containsKey(l)) {
                    map.put(l, map.get(l) + 1);
                    if (map.get(l) > 0) cnt--;
                }
                lo++;
            }
      
        }
        return false;
    }
}