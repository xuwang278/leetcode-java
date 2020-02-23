class Solution {
    public int videoStitching(int[][] clips, int T) {
        int ans = 0;
        Arrays.sort(clips, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] a, int [] b) {
        		return a[0] - b[0];
        	}
        });

        for (int i = 0, st = 0, end = 0; st < T; st = end, ans++) {
        	for (; i < clips.length && clips[i][0] <= st; i++) {
        		end = Math.max(end, clips[i][1]);
        	}
        	if (st == end) return -1;
        }
        return ans;
    }
}