class Solution {
    
    // Solution 1: 递推
    public boolean isAdditiveNumber(String num) {
    	if (num == null || num.length() == 0) return false;

    	for (int i = 1; i < num.length(); i++) { // i <= (num.length() - 1) / 2; 
    		for (int j = i + 1; j < num.length(); j++) { // num.length() - j >= j - i && num.length() - j >= i
    			// exhust every combination
    			String s1 = num.substring(0, i);
    			String s2 = num.substring(i, j);
    			String rest = num.substring(j);
    			if (isAdditiveNumber(s1, s2, rest)) return true;
    		}
    	}
    	return false;
    }

    private boolean isAdditiveNumber(String s1, String s2, String rest) {
    	if (rest.length() == 0) return true; // no more left
    	// Integer.parseInt("001") = 1
    	if ((s1.length() > 1 && s1.charAt(0) == '0') || (s2.length() > 1 && s2.charAt(0) == '0')) return false;

    	long i1 = Long.parseLong(s1); 
    	long i2 = Long.parseLong(s2);
    	String sum = Long.toString(i1 + i2); // String.valueOf(i1 + i2);
    	if (!rest.startsWith(sum)) return false;
    	return isAdditiveNumber(s2, sum, rest.substring(sum.length()));
    }


}
