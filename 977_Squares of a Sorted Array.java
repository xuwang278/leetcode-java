class Solution {
    // T: O(n)
    // S: O(1)
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int lo = 0, hi = n - 1;
        int[] ans = new int[n];
        int i = n - 1;
        // 最大平方出现在两端
        while (lo <= hi) {
            if (Math.abs(A[lo]) > Math.abs(A[hi])) {
                ans[i--] = A[lo] * A[lo];
                lo++;
            } else {
                ans[i--] = A[hi] * A[hi];
                hi--;
            }
        }
        return ans;
    }
    
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }
}
