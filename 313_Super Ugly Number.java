class Solution {
	// p264 ugly number II
	// T: O(n)
    // S: O(n)
    public int nthSuperUglyNumber(int n, int[] primes) {
    	int[] res = new int[n];
    	int[] indexes = new int[primes.length]; 
    	res[0] = 1;
    	for (int i = 1; i < n; i++) {
    		int next = Integer.MAX_VALUE;
    		
    		// generate ugly numbers and select min
    		for (int j = 0; j < indexes.length; j++) 
    			next = Math.min(next, res[indexes[j]] * primes[j]);
    		
    		// increment index
    		for (int j = 0; j < indexes.length; j++) 
    			if (next == res[indexes[j]] * primes[j]) indexes[j]++;
    		
    		res[i] = next;
    	}

    	return res[n - 1];
    }

}