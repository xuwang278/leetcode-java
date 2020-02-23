class Solution {
    public boolean judgeSquareSum(int c) {
        int lo = 0, hi = (int) Math.sqrt(c);
        while (lo <= hi) {
        	int cur = lo * lo + hi * hi;
        	if (cur < c) lo++;
        	else if (cur > c) hi--;
        	else return true;
        }
        return false;
    }

    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) return true;
        }
        return false;
    }
}