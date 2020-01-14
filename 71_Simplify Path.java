class Solution {
	// T: O(n)
	// S: O(n)
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        for (String s : paths) {
        	if (s.equals("..")) { // go to upper level
        		if (!stack.isEmpty()) stack.pop(); 
        	} else if (!s.equals(".") && !s.equals("")) { // a valid path
        		stack.push(s); // go to next level
        	}

        	// if (s.equals("..")) {
         //        if (stack.isEmpty()) continue;
         //        else stack.pop();
         //    } else {
         //        if (s.equals(".") || s.equals("")) continue;
         //        else stack.push(s);
         //    }

        }
        
        if (stack.isEmpty()) return "/";
        String res = "";
        while (!stack.isEmpty()) {
        	res = "/" + stack.pop() + res;
        }
        return res;
    }
}