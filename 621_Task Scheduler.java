class Solution {
    // latest version
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>(); // char -> freq
        for (char c : tasks) 
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(b, a);
                // return b.compareTo(a);
                // return b - a;
            }
        });
            
        for (int i : map.values()) pq.offer(i); // load tasks to max-heap
         
        int intervals = 0;
        while (!pq.isEmpty()) {
            List<Integer> pulse = new ArrayList<>();
            
            // get n + 1 (or less) distinct tasks from hi to lo freq
            for (int i = 0; i <= n && !pq.isEmpty(); i++) 
                pulse.add(pq.poll());
            
            for (int i : pulse)
                if (--i > 0) pq.offer(i); // reduce # and load to heap
            
            // if pq is empty, all works have been done, plus the actual # of pulse;
            // or plus n + 1 (idle if less than n+1 distinct asks)
            intervals += pq.isEmpty() ? pulse.size() : n + 1; 
        }
        return intervals;
    }

    // Greddy: always start task with most freq to get least usage of idel
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char t : tasks) map[t - 'A']++;
            
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int i : map) {
            if (i != 0) pq.offer(i);
        }
        
        int cycles = 0;
        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>(); // tasks processed in current iteration
            
            for (int i = 0; i <= n && !pq.isEmpty(); i++) {
                temp.add(pq.poll());
            }

            for (int i : temp) {
                if (--i > 0) pq.offer(i); // reduce freq and offer to pq
            }
            
            // if all tasks are processed, increment cycle with # of tasks last processed
            // otherwise, n + 1
            cycles += pq.isEmpty() ? temp.size() : n + 1; 
        }

        return cycles;
    }
}