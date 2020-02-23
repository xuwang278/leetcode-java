class Solution {
	// T: O(n)
	// S: O(n)
    public boolean wordPattern(String pattern, String str) {
    	if (pattern == null || str == null || pattern.length() == 0 || str.length() == 0)
    		return false;

    	char[] patternArray = pattern.toCharArray();
    	String[] strArray = str.split(" ");

    	if (patternArray.length != strArray.length) return false;

    	Map<Character, String> map = new HashMap<>();
    	for (int i = 0; i < patternArray.length; i++) {
    		if (!map.containsKey(patternArray[i])) {
    			if(map.containsValue(strArray[i])) 
    				return false;
    			map.put(patternArray[i], strArray[i]);
    		} else {
    			if (!map.get(patternArray[i]).equals(strArray[i]))
    				return false;
    		}
    	}

    	return true;
    }

    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        int n = pattern.length();
        String[] strs = str.split(" ");

        if (n != strs.length) return false;

        for (int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(strs[i])) return false;
            } else {
                if (map.containsValue(strs[i])) return false;
                map.put(c, strs[i]);
            }
        }
        return true;
    }

}