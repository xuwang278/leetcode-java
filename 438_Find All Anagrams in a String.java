class Solution {
    // Sol 1: sliding window | one map
    // Assumption: s and t are both not null
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return ans;
        
        Map<Character, Integer> map = new HashMap<>(); // char -> freq
        for (char c : p.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        
        int lo = 0, hi = 0, cnt = 0;
        while (hi < s.length()) {
            // accumulate chars
            char r = s.charAt(hi);
            if (map.containsKey(r)) {
                map.put(r, map.get(r) - 1);
                if (map.get(r) == 0) cnt++;
            }
            hi++;
            
            // when all chars in p found, find valid anagrams
            while (cnt == map.size()) {
                // lo is a valid start for an anagram as long as 
                // it is updated inside the while loop which requires
                // cnt == map.size()
                if (hi - lo == p.length()) ans.add(lo); 
                char l = s.charAt(lo);
                if (map.containsKey(l)) {
                    map.put(l, map.get(l) + 1);
                    if (map.get(l) > 0) cnt--;
                }
                lo++;
            }
        }
        
        return ans;
    }

    // Solution 2: sliding window | two maps
    public List<Integer> findAnagrams(String s, String p) {
        int ls = s.length();
        int lp = p.length();
        List<Integer> ans = new ArrayList<>();
        int[] maps = new int[26];
        int[] mapp = new int[26];
        for (char c : p.toCharArray()) mapp[c - 'a']++;
        for (int i = 0; i < ls; i++) {
            // when i >= lp, always delete one before add another
            // so that maps.size === lp
            if (i >= lp) maps[s.charAt(i - lp) - 'a']--;
            maps[s.charAt(i) - 'a']++;
            if (equal(maps, mapp)) ans.add(i + 1 - lp);
        }
        return ans;
    }

    // O(26)
    // if the two maps have the same content
    private boolean equal(int[] a, int[] b) {
        for (int i = 0; i < 26; i++)
            if (a[i] != b[i]) return false;
        return true;
    }

}