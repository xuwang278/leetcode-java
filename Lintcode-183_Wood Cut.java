public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        if (L == null || L.length == 0) return -1;
        
        int  max = Integer.MIN_VALUE;
        for (int wood : L) {
            max = Math.max(max, wood);
        }
        
        // find max idx such that countWood(L, idx) >= k
        int lo = 1, hi = max; // [lo, hi]
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = countWood(L, mid);
            if (cnt >= k) lo = mid;
            else hi = mid;
        }
        if (countWood(L, hi) >= k) return hi;
        if (countWood(L, lo) >= k) return lo;
        return -1;
    }
    
    private int countWood(int[] woods, int cutLen) {
        int cnt = 0;
        for (int wood : woods) {
            cnt += wood / cutLen;
        }
        return cnt;
    }
}