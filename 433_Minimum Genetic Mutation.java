class Solution {
	// BFS
	// T: O(n^2)
	// S: O(n)
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);
        int mutations = 0;
        while (!q.isEmpty()) {
        	int size = q.size();
        	for (int i = 0; i < size; i++) {
        		String cur = q.poll();
        		if (cur.equals(end)) return mutations;
        		for (String gene : bank) {
        			if (visited.contains(gene) || !validMutation(cur, gene)) continue;
        			visited.add(gene);
        			q.offer(gene);
        		}
        	}
        	mutations++;
        }
        return -1;
    }

    // Is s1 and s2 one character changed?
    private boolean valid(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                cnt++;
                if (cnt > 1) return false;
            
            }
        }
        return true;
    }
    
}