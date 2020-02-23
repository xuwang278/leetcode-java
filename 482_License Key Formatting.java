class Solution {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
        	if (s.charAt(i) != '-') 
        		sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        }
        return sb.reverse().toString().toUpperCase();
    }

    public String licenseKeyFormatting(String S, int K) {
    	String str = S.toUpperCase().replace("-", "");
    	StringBuilder sb = new StringBuilder();
    	int cnt = 0;
    	for (int i = str.length() - 1; i >= 0; i--) {
    		sb.append(str.charAt(i));
    		cnt++; // length of alphanumeric characters vs. sb.length() includes dashes
    		if (cnt % K == 0 && i != 0) sb.append("-");
    	}
    	return sb.reverse().toString();
    }
}