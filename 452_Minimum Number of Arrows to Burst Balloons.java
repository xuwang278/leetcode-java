class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] a, int[] b) {
        		return a[1] - b[1];
        	}
        });

        int right = points[0][1], cnt = 1;
        for (int[] p : points) {
        	if (p[0] > right) {
        		right = p[1];
        		cnt++;
        	}
        }
        return cnt;
    }
}