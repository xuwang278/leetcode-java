class Solution {
    
    // Solution 1: generate all 1690 ugly numbers <= 2^31 - 1
    // then sort and return the nth one
    // T: (NlogN) N = 1690
    // S: O(N) N = 1690
    public int nthUglyNumber(int n) {
    	List<Integer> list = new ArrayList<>();
    	for (long a = 1; a <= Integer.MAX_VALUE; a *= 2) 
    		for (long b = a; b <= Integer.MAX_VALUE; b *= 3)
    			for (long c = b; c <= Integer.MAX_VALUE; c *= 5)
    				list.add((int) c);

    	Collections.sort(list);
    	return list.get(n - 1);
    }

    // Solution 2: generate in order
    // T: O(n)
    // S: O(n)
    public int nthUglyNumber(int n) {
    	int[] res = new int[n];
    	res[0] = 1;
    	int i2 = 0, i3 = 0, i5 = 0;
    	for (int i = 1; i < n; i++) {
    		int next2 = res[i2] * 2;
    		int next3 = res[i3] * 3;
    		int next5 = res[i5] * 5;
    		int next = Math.min(next2, Math.min(next3, next5));
    		res[i] = next;

    		if (next == next2) i2++; // when next2 == next3, increment both i2 and i3
    		if (next == next3) i3++;
    		if (next == next5) i5++;
    	}
    	return res[n - 1];
    }
    
}