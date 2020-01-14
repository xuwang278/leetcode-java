class Solution {
    public int lengthOfLastWord(String s) {
    	// trim(): eliminates leading and trailing spaces
    	// lastIndexOf(): returns the index of the last occurrence of 
    	// the character in the character sequence
        return s.trim().length() - s.trim().lastIndexOf(' ') - 1;
    }
}