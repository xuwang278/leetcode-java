public class Solution {
    // Solution 1: min heap
    // T: O(n + n logk)
    // S: O(n)
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // num -> freq
        for (int n : nums) {
            // if (map.containsKey(n)) map.put(n, map.get(n) + 1);
            // else map.put(n, 1);
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        // min heap
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                int freq1 = e1.getValue();
                int freq2 = e2.getValue();
                return freq1 - freq2; // natural order gives min heap
            }
        });
        
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.offer(e);
            if (pq.size() > k) pq.poll();
        }
        
        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) 
            ans.add(0, pq.poll().getKey()); 
        
        return ans;
    }

    // Solution 3: treeMap
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // num -> freq
        for (int n : nums) 
            map.put(n, map.getOrDefault(n, 0) + 1);

        TreeMap<Integer, List<Integer>> tree = new TreeMap<>(); // freq -> {nums}
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (!tree.containsKey(freq))
                tree.put(freq, new ArrayList<>());
            tree.get(freq).add(num);
        }

        List<Integer> ans = new ArrayList<>();
        while (ans.size() < k) {
            Map.Entry<Integer, List<Integer>> e = tree.pollLastEntry();
            ans.addAll(e.getValue());
        }
        return ans;
    }

    // other ways to initialize pq
    public List<Integer> topKFrequent(int[] nums, int k) {
        // ...
        // PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new SortByFreq()); // min heap
        // ...
    }

    private class SortByFreq implements Comparator<Map.Entry<Integer, Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            int freq1 = e1.getValue();
            int freq2 = e2.getValue();
            return freq1 - freq2;
        }
    }

    // Solution 2: max heap
    // T: O(n + nlogn)
    // S: O(n)
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); // num -> freq
        for (int n : nums) 
            map.put(n, map.getOrDefault(n, 0) + 1);

        // max heap
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                int freq1 = e1.getValue();
                int freq2 = e2.getValue();
                return freq2 - freq1; 
            }
        });
        
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.offer(e);
        }
        
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < k && !pq.isEmpty()) {
            ans.add(pq.poll().getKey());
        }
        
        return ans;
    }

    

    // Solution 4: Bucket
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) 
            map.put(n, map.getOrDefault(n, 0) + 1);
        
        // bucket[freq]: list of items with freq
        // index: freq
        List<Integer>[] cnt = new List[nums.length + 1];     
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (cnt[freq] == null) cnt[freq] = new ArrayList<>();
            cnt[freq].add(key);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = cnt.length - 1; i > 0 && res.size() < k; i--)
            if (cnt[i] != null) res.addAll(cnt[i]);
        
        return res;
    }

}
