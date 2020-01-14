class Solution {
    public int minCut(String s) {
        int[][] cuts = new int[s.length()][s.length()];
        for (int l = 0; l < s.length(); l++) { // length of substring
            for (int i = 0; i + l < s.length(); i++) { // start of substring
                String str = s.substring(i, i + l + 1); // include char at i+l
                if (isPalindrome(str)) cuts[i][i + l] = 0;
                else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < i + l; k++) { // cut position
                        min = Math.min(min, cuts[i][k] + cuts[k + 1][i + l]);
                    }
                    cuts[i][i + l] = 1 + min;
                }
            }
        }
        return cuts[0][s.length() - 1]; // # of cuts for the entire string
    }

    private boolean isPalindrome(String s) {
        if (s.length() == 1) return true;
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) return false;
            lo++;
            hi--;
        }
        return true;
    }

}