class Solution {
    // [lo, hi]
    public int mySqrt(int x) {
        int lo = 1, hi = x;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid > x / mid) hi = mid - 1;
            else lo = mid + 1;
        }
        // lo is the smallest # such that lo^2 > x, i.e. (lo - 1)^2 <= x
        return lo - 1;
    }

    // [lo, hi)
    // upper_bound template
    // the smallest mid such that mid^2 > x
    // then the answer is mid - 1
    public int mySqrt(int x) {
        long lo = 1, hi = (long) x + 1; // x + 1 over flow when x == Integer.MAX_VALUE
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid > x / mid) hi = mid;
            else lo = mid + 1;
        }
        // lo is the first item that lo^2 > x, i.e. lo > sqrt(x)
        return (int) (lo - 1);
    }

    public int mySqrt(int x) {   
        int lo = 1, hi = x;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid > x / mid) hi = mid;
            else lo = mid;
        }
        if (lo > x / lo) return lo - 1;
        if (hi > x / hi) return hi - 1;
        return hi;
    }

}