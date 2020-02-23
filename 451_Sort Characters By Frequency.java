class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                int v1 = e1.getValue();
                int v2 = e2.getValue();
                return v2 - v1;
            }
        });
        
        for (Map.Entry<Character, Integer> e : map.entrySet())
            pq.offer(e);
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> top = pq.poll();
            char c = top.getKey();
            int freq = top.getValue();
            for (int i = 0; i < freq; i++)
                sb.append(c);
        }
        
        return sb.toString();
        
    }
}