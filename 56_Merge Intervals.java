/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    // Sol 1: sort + learn scan
	// T: O(nlogn + n)
	// S: O(n + n)
	public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) 
            return new int[][] {};
        
        // sort by left ending
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0]; // left end
            }
        });
        
        List<int[]> list = new ArrayList<>(); 
        int l = intervals[0][0];
        int r = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int nl = intervals[i][0]; // new left end
            int nr = intervals[i][1]; // new right end
            if (nl <= r) r = Math.max(r, nr); // combine two intervals to one bigger
            else {
                list.add(new int[] {l, r});
                l = nl;
                r = nr;
            }
        }
        
        list.add(new int[] {l, r}); // load the last interval
        
        int[][] ans = new int[list.size()][2]; // convert to ans
        for (int i = 0; i < list.size(); i++)
            ans[i] = list.get(i);
        return ans;
    }

    // Sol 2: sort + pq
    // T: O(nlogn + nlogn)
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) 
            return new int[][] {};
        
        // sort by left end
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        // MAX-heap sorted by right end
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        
        pq.offer(intervals[0]); // 最左的interval
        for (int i = 1; i < intervals.length; i++) {
            int[] top = pq.poll(); // 下一个右端点最远的interval
            if (top[1] < intervals[i][0]) { // cannot merge
                pq.offer(intervals[i]);
            } else {
                top[1] = Math.max(top[1], intervals[i][1]); // extend right end
            }
            pq.offer(top);
        }
        
        int[][] ans = new int[pq.size()][2];
        int i = pq.size() - 1;
        while (!pq.isEmpty()) {
            ans[i--] = pq.poll();
        }
        return ans;
    }
    
}