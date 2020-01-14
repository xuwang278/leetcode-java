class Solution {
    public boolean isOneEditDistance(String s, String t) {
    	int ls = s.length(), lt = t.length();
    	for (int i = 0; i < Math.min(ls, lt); i++) {
    		if (s.charAt(i) != t.charAt(i)) {
                // s.len == t.len, s[i] != t[i]
                // if the rest are the same, replace s[i] with t[i]
    			if (ls == lt) return s.substring(i + 1).equals(t.substring(i + 1));

                // t is longer than s, so the only possibility is 
                // deleting one char from t
    			if (ls < lt) return s.substring(i).equals(t.substring(i + 1));

                // s is longer than t, so the only possibility is deleting one char from s
    			else return t.substring(i).equals(s.substring(i + 1)); 
    		}
    	}
    	//All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
    	return Math.abs(ls - lt) == 1;
    }
}


