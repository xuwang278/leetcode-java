class Solution {
    public List<String> topKFrequent(String[] words, int k) {
    	Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
       		map.put(w, map.getOrDefault(w, 0) + 1);
        }

        // min-heap as of freq
        // max-heap as of string
        Queue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
        	@Override
        	public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        		int v1 = e1.getValue();
        		int v2 = e2.getValue();
        		if (v1 != v2) return v1 - v2; // min heap

        		String k1 = e1.getKey();
        		String k2 = e2.getKey();
        		return k2.compareTo(k1);
        	}
        });

        for (Map.Entry<String, Integer> e : map.entrySet()) {
        	pq.offer(e);
        	if (pq.size() > k) pq.poll();
        }

        List<String> ans = new ArrayList<>();
        while (!pq.isEmpty()) 
        	ans.add(0, pq.poll().getKey());

        return ans;
    }
}