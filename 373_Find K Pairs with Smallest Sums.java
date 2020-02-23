class Solution {
	// Solution 1: max heap: using the top k capacity of pq
	// T: O(mn * logk), m - nums1.length; n - nums2.length
	// S: O(k)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // max heap: pq maintains the top k largest pairs of two #, sorted by sum of the two;
        // in the end, pq.peek() is the k smallest item
        Queue<List<Integer>> pq = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> l1, List<Integer> l2) {
                int sum1 = l1.get(0) + l1.get(1);
                int sum2 = l2.get(0) + l2.get(1);
                return sum2 - sum1;
            }
        });
        
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                List<Integer> list = new ArrayList<>();
                list.add(n1);
                list.add(n2);
               // List<Integer> list = new ArrayList<>(Arrays.asList(n1, n2));
                pq.offer(list);
                if (pq.size() > k) pq.poll();
            }
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        while (!pq.isEmpty()) 
            ans.add(0, pq.poll());
        
        return ans;
    }

    // Solution 2: min heap: using the peek of pq
    // use the sorting feature of nums1 and nums2 - taking candidates in order
    // T: O(klogk)
    // S: O(k)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 2) return ans;
        
        Queue<Pair> pq = new PriorityQueue<>(); // natural order - min heap

        // load the first n smallest items
        for (int i = 0; i < nums2.length; i++) pq.offer(new Pair(0, i, nums1[0] + nums2[i]));
    
        // load the next possible ceiling item if any
        while (ans.size() < k && !pq.isEmpty()) {
            Pair pair = pq.poll();
            ans.add(pair.getList(nums1, nums2));
            if (pair.i + 1 == nums1.length) continue;
            pq.offer(new Pair(pair.i + 1, pair.j, nums1[pair.i + 1] + nums2[pair.j]));
        }

        return ans;
    }

    // pair of nums1[i], nums2[j]
    private static class Pair implements Comparable<Pair> {
        private int i;
        private int j;
        private int sum;

        public Pair(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair that) {
            return this.sum - that.sum;
        }

        // put nums1[i] and nums2[j] to list
        public List<Integer> getList(int[] nums1, int[] nums2) {
            List<Integer> list = new ArrayList<>();
            list.add(nums1[i]);
            list.add(nums2[j]);
            return list;
        }
    }

    // Sol 3: Binary Search
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int lo = 
    }

    
}

