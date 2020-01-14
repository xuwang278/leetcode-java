class Solution {
	// Brute Force: 2^4 -> 2*2 -> 2*2 -> 2*2
	// Binary Search: 2^4 -> 4^2 -> 16
	// T: O(logn)
	// S: O(n)
    public double myPow(double x, int n) {
        if (n > 0) return pow(x, n);
        // no negation from Integer.MIN_VALUE to (Integer.MAX_VALUE + 1)
        // overflow won't happen
        return 1 / pow(x, n); 
    }
	
	private double pow(double x, int n) {
		if (n == 0) return 1;
		double y = pow(x, n / 2);
		if (n % 2 == 0) return y * y;
		else return y * y * x;
	}

	// T: O(logn)
	// S: O(1)
	// -Integer.MIN_VALUE = Integer.MAX_VALUE + 1 -> overflow
	public double myPow(double x, int n) { 
		if (n == 0 || x == 1) return 1;
		double res = 1;
		long N = Math.abs((long)n); // avoid overflow
		// n = 8: 8, 4, 2, 1 (res involves)
		// n = 9: 9, 4, 2, 1 (res involves)
		while (N > 0) {
			if (N % 2 != 0) res *= x;
			x *= x;
			N /= 2;
		}
		if (n < 0) return 1.0 / res;
		return res;
	}

}