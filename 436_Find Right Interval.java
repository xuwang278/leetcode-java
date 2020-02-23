class Solution {
    // Solution 1: TreeMap
    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return new int[]{};
        }
        
        int[] ans = new int[intervals.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i); // start -> index
        }
        
        for (int i = 0; i < intervals.length; i++) {
            // ceiling of end
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(intervals[i][1]);
            ans[i] = entry != null ? entry.getValue() : -1;
        }

        return ans;
    }

    // Solution 2: Sort and Binary Search
    public int[] findRightInterval(int[][] intervals) {
        Map<Integer, Integer> map = new HashMap<>();
        int[][] origin = intervals.clone();

        for (int i = 0; i < intervals.length; i++) map.put(intervals[i][0], i);
        
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] res = new int[intervals.length];
        for (int i = 0; i < origin.length; i++) 
            res[i] = binarySearch(intervals, origin[i][1], map);
        
        return res;
    }

    // to find the smallest start value that is equal or larger than target
    // intervals is sorted by start value
    private int binarySearch(int[][] intervals, int target, Map<Integer, Integer> map) {
        int lo = 0, hi = intervals.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (intervals[mid][0] < target) lo = mid; // move to hi
            else if (intervals[mid][0] > target) hi = mid;
            else return map.get(intervals[mid][0]);
        }

        // 如果lo hi 都合法，可返回任何一个，顺序不重要
        if (intervals[lo][0] >= target) return map.get(intervals[lo][0]);
        else if (intervals[hi][0] >= target) return map.get(intervals[hi][0]);
        else return -1;
    }
}