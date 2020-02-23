class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) 
            map.put(order.charAt(i), i);
        
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            if (greater(first, second, map)) return false;
        }
        
        return true;
    }
    
    private boolean greater(String s, String t, Map<Character, Integer> map) {
        for (int i = 0; i < s.length() && i < t.length(); i++) {
            char l = s.charAt(i);
            char r = t.charAt(i);
            if (map.get(l) > map.get(r)) return true;
            else if (map.get(l) < map.get(r)) return false;
        }
        return s.length() > t.length();
    }


    // Sol 2:
    public boolean isAlienSorted(String[] words, String order) {
        int[] cnt = new int[26];
        for (int i = 0; i < order.length(); i++) {
            cnt[order.charAt(i) - 'a'] = i;
        }
        
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            // fisrt should be smaller than second
            if (compare(first, second, cnt) > 0) return false;
        }
        return true;
    }
    
    // return neg, if a < b
    // return 0, if a == b
    // return pos, if a > b
    private int compare(String a, String b, int[] cnt) {
        int i = 0, j = 0;
        int cmp = 0;
        while (i < a.length() && j < b.length()) {
            cmp += cnt[a.charAt(i) - 'a'] - cnt[b.charAt(j) - 'a'];
            if (cmp != 0) break;
            i++;
            j++;
        }
        return cmp == 0 ? a.length() - b.length() : cmp;
    }
}