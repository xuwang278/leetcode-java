class Solution {
    // T: (log3n)
    // S: O(1)
    public boolean isPowerOfThree(int n) {
        long p = 1; // n == Integer.MAX_VALUE
        while (p < n) p *= 3;
        return p == n;
    }

    // T: (log3n)
    // S: O(1)
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false; // if (n == 0) return false;
        while (n % 3 == 0) n /= 3;
        return n == 1;
    }
}
