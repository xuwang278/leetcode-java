class Solution {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        
        int lo = 0, hi = num.length() - 1;
        while (lo <= hi) {
            char l = num.charAt(lo);
            char r = num.charAt(hi);
            if (!map.containsKey(l) || !map.containsKey(r) || map.get(l) != r)
                return false;
            
            lo++;
            hi--;
        }
        return true;
    }
}