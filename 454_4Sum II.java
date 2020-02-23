class Solution {
	// TLE
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int s = 0; s < C.length; s++) {
                    for (int t = 0; t < D.length; t++) {
                        if (A[i] + B[j] + C[s] + D[t] == 0) cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    	Map<Integer, Integer> map = new HashMap<>();

    	// all possible sums -> freq
    	for (int i = 0; i < C.length; i++) {
    		for (int j = 0; j < D.length; j++) {
    			int sum = C[i] + D[j];
    			map.put(sum, map.getOrDefault(sum, 0) + 1);
    		}
    	}

    	int ans = 0;
    	for (int i = 0; i < A.length; i++) {
    		for (int j = 0; j < B.length; j++) {
                int lookFor = -(A[i] + B[j]); // expect to find it in map
    			ans += map.getOrDefault(lookFor, 0);
    		}
    	}

    	return ans;
    }
}

