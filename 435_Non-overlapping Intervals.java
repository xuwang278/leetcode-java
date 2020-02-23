class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        int end = intervals[0][1], cnt = 1;
        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                end = interval[1];
                cnt++;
            }
        }

        return intervals.length - cnt;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        // build heap O(n)?
        for (int[] interval : intervals) pq.offer(interval);
        int end = Integer.MIN_VALUE, cnt = 0;
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            if (cur[0] >= end) {
                end = cur[1];
                cnt++;
            }
        }
        return intervals.length - cnt;
    }
}