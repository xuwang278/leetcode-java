class Solution {
	// T: O(n + nlogn 构图 + n 遍历) n - # of itinerary
	// S: O(n)
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> g = new HashMap<>();
        for (String[] t : tickets) {
            String from = t[0];
            String to = t[1];
            if (!g.containsKey(from)) g.put(from, new PriorityQueue<String>());
            g.get(from).add(to);
        }   

        List<String> res = new ArrayList<>();
        dfs("JFK", g, res); // run dfs starting from "JFK"
        return res;
    }

    private void dfs(String from, Map<String, PriorityQueue<String>> g, List<String> res) {
        PriorityQueue<String> pq = g.get(from);
        while(pq != null && !pq.isEmpty()) dfs(pq.poll(), g, res);  
        res.add(0, from); // reverse postorder
    }

    // Iterative
    // T: O(n) n - # of itinerary
	// S: O(n)
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
        	String from = ticket[0];
        	String to = ticket[1];
        	if (!map.containsKey(from))
        		map.put(from, new PriorityQueue<String>());
        	map.get(from).add(to);
        }	

        List<String> res = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            // String from = stack.peek(); // code is wrong because peek of stack is changing dynamically
        	while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty())
        		stack.push(map.get(stack.peek()).poll());

        	res.add(0, stack.pop());
        }
	    return res;
    }

}