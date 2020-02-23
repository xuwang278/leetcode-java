class Solution {
    // T: O(logn)
    // S: O(1)
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    // T: O(1)
    // S: O(1)
    public boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;    
    }

    // T: O(1)
    // S: O(1)
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }
}