class Solution {
    public int lengthLongestPath(String input) {
        // if (input == null || input.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int result = 0;
        for (String s : input.split("\n")) {
        	int level = s.lastIndexOf("\t") + 1; // # of "\t"
        	int len = s.length() - level;

        	// if current directory/file depth is lower than that the top directory/file on the stack, pop from stack 
        	while (level + 1 < stack.size()) stack.pop(); // find parent

        	if (s.contains(".")) result = Math.max(result, stack.peek() + len);
        	else stack.push(stack.peek() + len + 1); // +1 to include "\"
        }
        return result;
    }
}


String str = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
String[] in = str.split("\n");
for (String s : in) System.out.println(s);
System.out.println(in[1].length());

dir
	subdir1
	subdir2
		file.ext
8
	// Solution 2
	public int lengthLongestPath(String input) {
	    String[] paths = input.split("\n");
	    int[] stack = new int[paths.length+1];
	    int maxLen = 0;
	    for(String s : paths){
	        int lev = s.lastIndexOf("\t") + 1, curLen = stack[lev + 1] = stack[lev] + s.length() - lev + 1;
	        if(s.contains(".")) maxLen = Math.max(maxLen, curLen - 1);
	    }
	    return maxLen;
	}

	// Solution 3
	public int lengthLongestPath(String input) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 0);
        int result = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf('\t') + 1;
            int len = s.length() - level;
            if (s.contains(".")) {
                result = Math.max(result, hashMap.get(level) + len);
            } else {
                hashMap.put(level + 1, hashMap.get(level) + len + 1);
            }
        }
        return result;
    }




    