class Solution {
	// Solution 1: minHeap
	// T: O(nlogn)
	// S: O(n)
	public int[][] kClosest(int[][] points, int K) {
        // PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] p1, int[] p2) {
        //         int d1 = p1[0] * p1[0] + p1[1] * p1[1];
        //         int d2 = p2[0] * p2[0] + p2[1] * p2[1];
        //         return d1 - d2; // min heap
        //     }
        // });

        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> (p1[0] * p1[0] + p1[1] * p1[1]) - (p2[0] * p2[0] + p2[1] * p2[1]));

        for (int[] p : points) {
        	pq.offer(p);
        }

        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++) {
        	ans[i] = pq.poll();
        }

        return ans;

     }

	// Solution 2: maxHeap
	// T: O(nlogK)
	// S: O(K)
    public int[][] kClosest(int[][] points, int K) {
        // PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] p1, int[] p2) {
        //         int d1 = p1[0] * p1[0] + p1[1] * p1[1];
        //         int d2 = p2[0] * p2[0] + p2[1] * p2[1];
        //         return d2 - d1; // max heap
        //     }
        // });
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1]));
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K)
                pq.poll();
        }
        
        int[][] ans = new int[K][2];
        int i = K - 1;
        while (!pq.isEmpty()) {
            ans[i--] = pq.poll();
        }
        
        return ans;
    }

    // Solution 3: Sort
    // T: O(nlogn)
    // S: O(n)
    public int[][] kClosest(int[][] points, int K) {
    	// Arrays.sort(points, new Comparator<int[]>() {
    	// 	@Override
     //        public int compare(int[] p1, int[] p2) {
     //            int d1 = p1[0] * p1[0] + p1[1] * p1[1];
     //            int d2 = p2[0] * p2[0] + p2[1] * p2[1];
     //            return d1 - d2;
     //        }
    	// });

    	Arrays.sort(points, (p1, p2) -> (p1[0] * p1[0] + p1[1] * p1[1]) - (p2[0] * p2[0] + p2[1] * p2[1]))
    	return Arrays.copyOfRange(points, 0, K);
    }

    // Solution 4: Quick Select
    // T: O(n)
    // S: O(n)
    public int[][] kClosest(int[][] points, int K) {
    	int lo = 0, hi = points.length - 1;
    	while (lo < hi) {
    		int i = partition(points, lo, hi);
    		if (i > K) hi = i - 1;
    		else if (i < K) lo = i + 1;
    		else return Arrays.copyOfRange(points, 0, i);
    	}
    	return Arrays.copyOfRange(points, 0, lo);
        // lo == hi: points is sorted
        // return Arrays.copyOfRange(points, 0, K);
    }

    // lo < hi
    // O(n)
    private int partition(int[][] points, int lo, int hi) {
    	int i = lo;
    	int j = hi + 1;
    	int[] v = points[lo];
    	while (true) {
    		while (less(points[++i], v)) {
    			if (i == hi) break;
    		}

    		while (less(v, points[--j])) {
    			if (j == lo) break;
    		}

    		if (i >= j) break;

    		exch(points, i, j);
    	}
    	exch(points, lo, j);
    	return j;
    }

    private boolean less(int[] p1, int[] p2) {
    	int d1 = p1[0] * p1[0] + p1[1] * p1[1];
        int d2 = p2[0] * p2[0] + p2[1] * p2[1];
        return d1 < d2;
    }

    private void exch(int[][] a, int i, int j) {
    	int[] swap = a[i];
    	a[i] = a[j];
    	a[j] = swap;
    }

}