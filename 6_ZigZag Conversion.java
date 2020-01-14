class Solution {
	// T: O(n)
	// S: O(n)
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
        	sb[i] = new StringBuilder();
        }

        int index = 0;
        while (index < s.length()) {
        	// go down
        	for (int i = 0; i < numRows && index < s.length(); i++) 
        		sb[i].append(s.charAt(index++));
        	// go up
        	for (int i = numRows - 2; i > 0 && index < s.length(); i--) // numRows starts from 1 not 0
        		sb[i].append(s.charAt(index++));
        }

        for (int i = 1; i < sb.length; i++) {
        	sb[0].append(sb[i]);
        }
        return sb[0].toString();
    } 

}