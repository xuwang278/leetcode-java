/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int lo = 0, hi = mountainArr.length() - 1;
        int peakIndex = findPeakIndex(mountainArr);
        
        int ans = binarySearch(mountainArr, target, 0, peakIndex);
        if (ans != -1) return ans;
        return binarySearchReverse(mountainArr, target, peakIndex + 1, mountainArr.length() - 1);
    }
    
    // 
    private int findPeakIndex(MountainArray mountainArr) {
        int lo = 0, hi = mountainArr.length() - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1))
                hi = mid;
            else lo = mid;
        }
        return mountainArr.get(lo) > mountainArr.get(hi) ? lo : hi;
    }
    
    private int binarySearch(MountainArray mountainArr, int target, int lo, int hi) {
        if (lo < 0 || hi >= mountainArr.length()) return -1;
        
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mountainArr.get(mid) == target) return mid;
            else if (mountainArr.get(mid) > target) hi = mid;
            else lo = mid;
        }
        if (mountainArr.get(lo) == target) return lo;
        if (mountainArr.get(hi) == target) return hi;
        return -1;
    }
    
    private int binarySearchReverse(MountainArray mountainArr, int target, int lo, int hi) {
        if (lo < 0 || hi >= mountainArr.length()) return -1;
        
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mountainArr.get(mid) == target) return mid;
            else if (mountainArr.get(mid) > target) lo = mid;
            else hi = mid;
        }
        if (mountainArr.get(lo) == target) return lo;
        if (mountainArr.get(hi) == target) return hi;
        return -1;
    }
}