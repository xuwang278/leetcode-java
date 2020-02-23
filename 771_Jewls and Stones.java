class Solution {
	// T: O(m + n)
	// S: O(128)
    public int numJewelsInStones(String J, String S) {
        int[] map = new int[128];
        for (char c : J.toCharArray()) {
            map[c]++;
        }
    
        int cnt = 0;
        for (char c : S.toCharArray()) {
            if (map[c] != 0) cnt++;
        }
        return cnt;
    }
}