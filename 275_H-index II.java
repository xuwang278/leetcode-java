class Solution {
    public int hIndex(int[] citations) {
        int lo = 0, len = citations.length, hi = len - 1;
        while (lo <= hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (citations[mid] == (len - mid)) return citations[mid];
        	else if (citations[mid] > (len - mid)) hi = mid - 1;
        	else lo = mid + 1;
        }
        return len - (hi + 1);
    }
}