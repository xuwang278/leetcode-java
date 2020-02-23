public class Solution extends VersionControl {
    // [lo, hi]
    public int firstBadVersion(int n) {
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo; // the smallest n such that isBadVersion(n) == true
    }

    // [lo, hi)
    // lower_bound: find the smallest x that isBadVersion(x) == true
    public int firstBadVersion(int n) {
        long lo = 1, hi = (long) n + 1;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (isBadVersion((int) mid)) hi = mid;
            else lo = mid + 1;
        }
        return (int) lo;
    }

    public int firstBadVersion(int n) {
        // smallest item n such that isBadVersion(n) == true;
        int lo = 1, hi = n;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) hi = mid;
            else lo = mid;
        }
        if (isBadVersion(lo)) return lo;
        if (isBadVersion(hi)) return hi;
        return hi + 1;
    }
}