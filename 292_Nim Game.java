class Solution {
	// T: O(1)
	// S: O(1)
    public boolean canWinNim(int n) {
        if (n == 0) return false;
        return n % 4 != 0;
    }
}