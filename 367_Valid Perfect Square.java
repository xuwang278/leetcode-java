class Solution {

	// square number is 1+3+5+7+...
	// T: O(sqrt(n))
    public boolean isPerfectSquare(int num) {
    	for (int i = 1; num > 0; i += 2) {
    		num -= i;
    	}
    	return num == 0;
    }

    // T: O(logn)
    public boolean isPerfectSquare(int num) {
    	int lo = 1, hi = num;
    	while (lo <= hi) {
    		long mid = lo + (hi - lo) / 2;
    		if (mid * mid == num) return true;
    		else if (mid * mid < num) lo = mid + 1;
    		else hi = mid - 1;
    	}
    	return false;
    }
    
    public boolean isPerfectSquare(int num) {
    	int lo = 1, hi = num;
    	while (lo + 1 < hi) {
    		long mid = lo + (hi - lo) / 2;
    		if (mid * mid == num) return true;
    		else if (mid * mid < num) lo = (int) mid;
    		else hi = (int) mid;
    	}
    	if (lo * lo == num) return true;
    	if (hi * hi == num) return true;
    	return false;
    }

}