class Solution {
	// T: O(n)
	// S: O(n)
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] buckets = new int[len + 1];

        // c goes to bucket[c] (c out of len, goes to bucket[len])
        for (int c : citations) {
        	if (c > len) buckets[len]++;
        	else buckets[c]++;
        }

        int count = 0;
        for (int i = len; i >= 0; i--) {
        	count += buckets[i];
        	if (count >= i) return i;
        }

        return 0;
    }

    // T: O(nlogn)
	// S: O(1)
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if (len <= citations[i]) return len;
            else len--;
        }
        return len;
    }

}