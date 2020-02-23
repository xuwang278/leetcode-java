class Solution {
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] cnt = new int[26];
		for (char i: magazine.toCharArray()) cnt[i - 'a']++;
		for (char j : ransomNote.toCharArray()) 
			if (--cnt[j - 'a'] < 0) return false;
		return true;
	}

	// if strings contain chars other than c26 letters 
	// cnt may be defined as size of 128 or 256
	// http://www.asciitable.com/

	// Doesn't have to be continuous 
    public boolean canConstruct(String ransomNote, String magazine) {
    	if (ransomNote == null || ransomNote.length() == 0) return true;  
        if (magazine == null || magazine.length() == 0) return false;

        int len = 0;
        int i = 0, j = 0;
        while (i < ransomNote.length()) {
        	char ch_rans = ransomNote.charAt(i);
        	char ch_maga = magazine.charAt(j);
        	if (ch_rans != ch_maga) {
        		if (i == 0) j++; 
        		else {
        			i = 0;
        			len = 0;
        		}
        	} else {
        		len++;
        		if (len == ransomNote.length()) return true;
        		i++;
        		j++;
        	}
        	if (j == magazine.length()) break;
        }
        return false;
    }
}

"bg"
 i
"efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"
        j

"fffbfg"
   i

"effjfggbffjdgbjjhhdegh"
    j

  len = 2