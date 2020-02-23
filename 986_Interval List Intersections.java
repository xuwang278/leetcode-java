class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
    	List<int[]> list = new ArrayList<>();
    	int i = 0, j = 0;
    	while (i < A.length && j < B.length) {
            // find intersection
    		int start = Math.max(A[i][0], B[j][0]);
    		int end = Math.min(A[i][1], B[j][1]);

    		if (start <= end) // valid
                list.add(new int[] {start, end});

    		// increment pointer with smaller end
            // because the longer tail may intersect with next one
    		if (A[i][1] > B[j][1]) j++;
    		else i++;
    	}
    	return list.toArray(new int[list.size()][2]);
    	// int[][] ans = new int[list.size()][2];
    	// for (int i = 0; i < ans.length; i++)
    	// 	ans[i] = list.get(i);
    	// return ans;
    }
}