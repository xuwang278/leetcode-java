class Solution {
	// TLE
    public int arrangeCoins(int n) {
        if (n <= 1) return n;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            if (sum > n) return i - 1;
        }
        return -1;
    }

    // O(logN)
    // Find the smallest n in a sorted sequence [1,2, ..., k], where (1+n)*n / 2 >= k
    public int arrangeCoins(int n) {
    	public int arrangeCoins(int n) {
        long nLong = (long) n;
    	long lo = 1, hi = nLong;
    	while (lo + 1 < hi) {
    		long mid = lo + (hi - lo) / 2;
    		if (mid * (mid + 1) < 2 * nLong) lo = mid;
    		else if (mid * (mid + 1) > 2 * nLong) hi = mid;
            else return (int) mid;
    	}
    	if (lo * (lo + 1) <= 2 * nLong) return (int) lo;
    	if (hi * (hi + 1) <= 2 * nLong) return (int) hi;
    	return -1;
    }

    // sum = (x + 1) * x / 2
    // so x = (-1 + sqrt(8 * n + 1)) / 2
    public int arrangeCoins(int n) {
        return (int)((-1 + Math.sqrt(1 + 8 * (long)n)) / 2);
    }
}
