class Solution {
    // T: O(nlogK + klogK)
    // S: O(K)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) 
            return new ArrayList<>();
        
        // max-heap: sorted by dist (further on top); tie by val (larger on top)
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (Math.abs(a - x) == Math.abs(b - x))
                    return b - a;
                return Math.abs(b - x) - Math.abs(a - x);
            }
        });
        
        // maintain a pq with capacity k
        // in the end, the k closest elements in pq
        for (int n : arr) {
            pq.offer(n);
            if (pq.size() > k) pq.poll();
        }
        
        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        
        Collections.sort(ans); // sort by val rather than dist
        return ans;
    }

    // Sol 2: bibary search, see 658.pptx
    // T: O(log(n-k) + k)
    // S: O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) return ans;

        int n = arr.length;
        int lo = 0, hi = n - k - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // x is closer to mid, left bound is less than mid, so narrow search range
            if (x - arr[mid] <= arr[mid + k] - x) hi = mid - 1;
            else lo = mid + 1; // otherwise left bound is larger than mid
        }

        // lo is the smallest index
        for (int i = 0; i < k; i++)
            ans.add(arr[lo + i]);
        return ans;
    }
    
     // Sol 3: sort
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> arrList = new ArrayList<>();
        for (int i : arr) arrList.add(i);
        
        Collections.sort(arrList, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (Math.abs(a - x) == Math.abs(b - x)) return a - b;
                else return Math.abs(a - x) - Math.abs(b - x);
            }
        });
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++)
            ans.add(arrList.get(i));
        
        Collections.sort(ans);
        return ans;
    }
}



