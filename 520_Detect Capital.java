class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() < 2) return true; 
        if (word.toUpperCase().equals(word)) return true; // all cap
        if (word.substring(1).toLowerCase().equals(word.substring(1))) return true; // only 1st Cap or all lower
        return false; // other cases
	}
}