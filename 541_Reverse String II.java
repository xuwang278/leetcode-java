class Solution {
    public String reverseStr(String s, int k) {
        char[] chs = s.toCharArray(); // flexble to modify
        for (int i = 0; i < chs.length; i += 2 * k) 
            reverseString(chs, i, Math.min(chs.length - 1, i + k - 1)); // trick to handle exception
        return String.valueOf(chs);
    }
    
    // same as LC 344
    public void reverseString(char[] chs, int lo, int hi) {
        while (lo < hi) {
            char swap = chs[lo];
            chs[lo] = chs[hi];
            chs[hi] = swap;
            lo++;
            hi--;
        }
    }

}

