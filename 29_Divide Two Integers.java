class Solution {

	Things to consider:
	1. + -
	2. overflow
	3. = 0 3/5
	4. normal

	// T: O(logN)
	// S: O(logN) not O(n)!!
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        //if (ldividend < ldivisor || ldividend == 0) return 0; // assume divisor > 0; also, Java will handle divisor == 0
        long lres = divide(ldividend, ldivisor);
        int res = 0;
        if (lres > Integer.MAX_VALUE) {
        	res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else res = (int) (sign * lres);
        return res;
    }

    private long divide(long ldividend, long ldivisor) {
    	if (ldividend < ldivisor) return 0;
    	long sum = ldivisor;
    	long multiple = 1; // # of ldivisior in ldividend
    	while ((sum + sum) < ldividend) {
    		sum += sum;
    		multiple += multiple;
    	}
    	return multiple + divide(ldividend - sum, ldivisor);
    }

}