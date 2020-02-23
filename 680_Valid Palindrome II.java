class Solution {
    public boolean validPalindrome(String s) {
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            char l = s.charAt(lo);
            char r = s.charAt(hi);
            if (l != r) {
                // remove either to check is the remaning is valid
                return isValid(s, lo + 1, hi) 
                       || isValid(s, lo, hi - 1);
            }
            lo++;
            hi--;
        }
        return true;
    }
    
    // check if s.substring(lo, hi + 1) is valid 
    private boolean isValid(String s, int lo, int hi) {
        while (lo < hi) {
            char l = s.charAt(lo);
            char r = s.charAt(hi);
            if (l != r) return false;
            lo++;
            hi--;
        }
        return true;
    }
}