class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
        	if (t.charAt(indexT) == s.charAt(indexS)) {
        		indexS++;
        		if (indexS == s.length()) return true;
        	}
        	indexT++;
        }
        return false;
    }

    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) return false;
        }
        return true;
    }
}