class Solution {
	// For each point, compute the distance to the rest of the points and count.
	// If there are k points that have the same distance to current point, then there are P(k,2) = k*k-1 Boomerangs.
	// T: O(n^2)
	// S: O(n)
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        // Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
        	Map<Integer, Integer> map = new HashMap<>();
        	for (int j = 0; j < points.length; j++) {
        		if (i == j) continue;
        		int d = getDist(points[i], points[j]);
        		map.put(d, map.getOrDefault(d, 0) + 1);
        	}

        	for (int val : map.values()) {
        		ans += val * (val - 1);
        	}
        	// map.clear();
        }
        return ans;
    }

    private int getDist(int[] a, int[] b) {
    	int dx = a[0] - b[0];
    	int dy = a[1] - b[1];
    	return dx * dx + dy * dy;
    }

}