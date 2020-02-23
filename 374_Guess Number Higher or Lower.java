public class Solution extends GuessGame {
	// T: O(n)
	// S: O(1)
    public int guessNumber(int n) {
        for (int i = 1; i <= n; i++) {
        	if (guess(i) == 0) return i;
        }
        return -1;
    }

    // T: O(logn)
	// S: O(1)

	// guess(i) == -1 if i > pick ("my number")
    public int guessNumber(int n) {
        int lo = 1, hi = n;
    	int i = 0;
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2;
    		if (guess(mid) == 0) return mid;
    		if (guess(mid) == -1) hi = mid - 1;
    		if (guess(mid) == 1) lo = mid + 1;
    	}
    	return -1;
    }

    public int guessNumber(int n) {
    	int lo = 1, hi = n;
    	while (lo + 1 < hi) {
    		int mid = lo + (hi - lo) / 2;
    		if (guess(mid) == 0) return mid;
    		if (guess(mid) == -1) hi = mid;
    		if (guess(mid) == 1) lo = mid;
    	}
    	if (guess(lo) == 0) return lo;
    	if (guess(hi) == 0) return hi;
    	return -1;
    }
}