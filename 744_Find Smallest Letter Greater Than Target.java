class Solution {
	// Sol 1: scan
	// T: O(n)
	// S: O(1)
	public char nextGreatestLetter(char[] letters, char target) {
        // increment c
        for (char c : letters)
        	if (c > target) return c;
        return letters[0];
    }

    // Sol 2: Set
    // T: O(n)
    // S: O(26)
    public char nextGreatestLetter(char[] letters, char target) {
    	boolean[] visited = new boolean[26];
    	for (char c : letters) visited[c - 'a'] = true;

    	while (true) {
    		if (++target > 'z') target = 'a'; // increment target
    		if (visited[target - 'a']) return target;
    	}

    }

    // Sol 3: binary search [)
    // T: O(logn)
    // S: O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        while (lo < hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (letters[mid] <= target) lo = mid + 1;
        	else hi = mid;
        }
        // lo must equal to hi
        return letters[lo % letters.length];
    }

    // []
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (letters[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        
        return letters[lo % letters.length];
    }
}