class Solution {
	// T: O(n)
    // S: O(n)
    public int countSegments(String s) {
        if (s == null || s.length() == 0) return 0;
        String trimmed = s.trim(); // remove leading and trailing spaces
        if (trimmed.length() == 0) return 0;
        return trimmed.split("\\s+").length;
    }

    // T: O(n)
    // S: O(1)
    public int countSegments(String s) {
        int segmentCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }
        return segmentCount;
    }
}