class Solution {
    // a natural simulation on human's method: 
    // (1) sort by starting; 
    // (2) define a min-heap by ending 
    // (3) put 1st meeting to pq (in a meeting room) 
    // (4) accommodate rest of meeting: 
    //     (i) poll the the soonest available room
    //     (ii) if available: use the same, extend ending time
    //     (iii) if not, offer a new room to pq
    //     (iV) offer the polled room for resorting
    // (5) return pq.size()

    // T: O(nlogn)
    // S: O(n)
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        // sorted by starting time
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0]; 
            }
        });
        
        // min-heap sorted by ending time
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1]; 
            }
        });
        
        pq.offer(intervals[0]); // the earliest meeting
        for (int i = 1; i < intervals.length; i++) {
            int[] top = pq.poll(); // 下一个最早结束的会议
            if (top[1] <= intervals[i][0]) 
                top[1] = intervals[i][1]; // same room, extend ending time
            else pq.offer(intervals[i]); // do in another room
            pq.offer(top); // triger pq ordering 
        }
        return pq.size();
    }

    //Sol 2
    // T: O(nlogn)
    // S: O(n)
    public int minMeetingRooms(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0, j = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[j]) rooms++;
            else j++;
        }
        return rooms;
    }

}