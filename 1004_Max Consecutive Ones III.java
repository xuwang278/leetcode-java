class Solution {
    // T: O(n)
    // S: O(1)
    // Two pointers
    public int longestOnes(int[] A, int K) {
        int max = 0, zero = 0;
        int lo = 0, hi = 0;
        while (hi < A.length) {
            if (A[hi] == 0) {
                zero++;
            }
            hi++;
            
            while (zero > K) {
                if (A[lo] == 0) zero--;
                lo++;
            }
            
            max = Math.max(max, hi - lo);
        }
        return max;
    }
}