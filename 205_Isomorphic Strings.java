class Solution {
    // T: O(n)
    // S: O(n)
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> map = new HashMap<>();
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            char si = s.charAt(i);
            char ti = t.charAt(i);
            if (map.containsKey(si)) {
                if (map.get(si) != ti) return false; // 对不上
            } else {
                if (map.containsValue(ti)) return false; // 对过了
                map.put(si, ti);
            }
        }
        return true;
    }
}

Compare Strings have same value: equals (Objects)
Compare two chars: == (primative type)