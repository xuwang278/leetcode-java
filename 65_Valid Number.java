class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean eSeen = false;
        boolean numSeen = false;
        boolean dotSeen = false;

        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (Character.isDigit(c)) {
        		numSeen = true;
        	} else if (c == 'e') {
        		if (eSeen || !numSeen) return false; // only one e is allowed and e shoud be after a num
        		eSeen = true;
        		numSeen = false; // need a num after e
        	} else if (c == '.') {
        		if (eSeen || dotSeen) return false; // no dot is allowed after e and only one . is allowed
        		dotSeen = true;
        	} else if (c == '-' || c == '+') {
        		if (i != 0 && s.charAt(i - 1) != 'e') return false; // sign only shows up at beginning or after e
        	} else return false;
        }

        return numSeen; // must see a num at least
    }
}