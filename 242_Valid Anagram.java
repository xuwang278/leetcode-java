class Solution {
	public boolean isAnagram(String s, String t) {
        int[] map_s = new int[128];
        int[] map_t = new int[128];
        for (char c : s.toCharArray()) {
            map_s[c - 'a']++;
        }
        
        for (char c : t.toCharArray()) {
            map_t[c - 'a']++;
        }
        
        for (int i = 0; i < 128; i++) {
            if (map_s[i] != map_t[i]) return false;
        }
        
        return true;
        
        
    }

	// Sol 1: sort
    // T: O(nlogn) + O(n) = O(nlogn)
    // S: O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();
        Arrays.sort(s_chars);
        Arrays.sort(t_chars);
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] != t_chars[i]) return false;
        }
        return true;
    }

    // Sol 2: map
    // T: O(n)
    // s: O(1) - no more than 26 chars
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i)))
                map.put(s.charAt(i), 1);
            else map.put(s.charAt(i), map.get(s.charAt(i)) + 1); // freq increment by one
        }

        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) return false;
            else map.put(t.charAt(i), map.get(t.charAt(i)) - 1); // freq decrement by one
        }

        for (int v : map.values()) {
            if (v != 0) return false;
        }
        return true;

    }

    // Sol 2': map
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) map.remove(c);
        }
        
        return map.isEmpty();
    }

    // T: O(n)
    // s: O(1) - no more than 26 chars
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i) - 'a']--;
            if (freq[t.charAt(i) - 'a'] < 0) return false;
        }

        return true;
    }

}