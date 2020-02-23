class Solution {
	// T: O(n)
	// S: O(n)
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
    	Stack<Integer> stack = new Stack<>();    	
    	String[] s = logs.get(0).split(":"); 
    	stack.push(Integer.valueOf(s[0])); // s[1] must be "start"
    	int prev = Integer.valueOf(s[2]); // starts the first task

    	for (int i = 1; i < logs.size(); i++) {
    		s = logs.get(i).split(":");
    		int func = Integer.valueOf(s[0]);
    		int time = Integer.valueOf(s[2]);
            
            // suspend previous thread and update its accumulated time
    		// push new thread on top of old one
    		if (s[1].equals("start")) {
    			if (!stack.isEmpty()) // check if there is a uncomplete thread
    				ans[stack.peek()] += time - prev;
    			stack.push(func);
    			prev = time; // starting time for next thread
    		} else {
    			ans[stack.pop()] += time - prev + 1;
    			prev = time + 1; // starting time for next thread
    		}
    	}
    	return ans;
    }

}

