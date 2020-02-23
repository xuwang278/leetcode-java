class Solution {
    // sort meeting intervals by starting time
    // iterate each meeting: check if the following meeting begins within
    // the time window be the current one: if so, return false
    // else, check next meeting and then return true
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0]; // sorted by start time
            }
        });
        
        // 1st meeting starting and ending time
        int s = intervals[0][0];
        int e = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int ns = intervals[i][0]; // next one
            int ne = intervals[i][1];
            if (ns < e) return false; 
            s = ns; // update: attend next meeting
            e = ne;
        }
        return true;
        
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        
        // min heap by starting time
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        for (int[] i : intervals) pq.offer(i);
        int[] first = pq.poll();
        int start = first[0];
        int end = first[1];
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int next_start = next[0];
            int next_end = next[1];
            if (next_start < end) return false;
            start = next_start;
            end = next_end;
        }
        return true;
    }
}