class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                // i points the last digit 
                numStack.push(num);
            } else if (c == '[') {
                strStack.push(sb.toString());
                sb = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(strStack.pop()); // base
                int repeatTimes = numStack.pop();
                for (int j = 0; j < repeatTimes; j++) {
                    temp.append(sb);
                }
                sb = temp;
            } else {
                sb.append(c); // char
            }
        }
        return sb.toString();
    }

	// does not work for nested coding
    public String decodeString(String s) {
        // assume input string is always valid
    	Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int freq = 0;
        for (char c : s.toCharArray()) {
        	if (Character.isDigit(c)) freq = Character.getNumericValue(c);
        	else if (Character.isLetter(c)) q.offer(c);
        	else if (c == ']') {

        		// build substring unit
        		String sub = "";
        		while (!q.isEmpty()) {
        			sub += q.poll();
        		}

        		// append to sb
        		for (int i = 0; i < freq; i++) {
        			sb.append(sub);
        		}

        	}
        }

        return sb.toString();
    }

    // Solution 1: stack
    public String decodeString(String s) {
    	String res = "";
    	Stack<Integer> countStack = new Stack<>();
    	Stack<String> resStack = new Stack<>();
    	int idx = 0;
    	while (idx < s.length()) {

    		// build repeating #
    		if (Character.isDigit(s.charAt(idx))) {
    			int count = 0;
    			while (Character.isDigit(s.charAt(idx))) {
    				count = 10 * count + (s.charAt(idx) - '0');
    				idx++;
    			}
    			countStack.push(count);
    		}

    		// save the latest substring and get ready for next one
    		else if (s.charAt(idx) == '[') {
    			resStack.push(res); // store last substring for future use
    			res = ""; // zero res to be ready for the coming substring 
    			idx++;
    		}

    		// build the latest formed substring
    		else if (s.charAt(idx) == ']') {
    			StringBuilder temp = new StringBuilder(resStack.pop());
    			int repeatTimes = countStack.pop();
    			for (int i = 0; i < repeatTimes; i++) temp.append(res);
    			res = temp.toString();
    			idx++;
    		} 

    		// forming substring
    		else {
    			res += s.charAt(idx++);
    		}
    	}

    	return res;
    }

    // Solution 2: DFS recursion
    private int pos = 0; // globally record index to check

	public String decodeString(String s) {
		StringBuilder sb = new StringBuilder();
		String num = "";

		for (int i = pos; i < s.length(); i++) {
			char c = s.charAt(i);

			// form substring
			if (c != '[' && c != ']' && !Character.isDigit(c)) {
				sb.append(c);
			} 

			// form repeating # 
			else if (Character.isDigit(c)) {
				num += c; // string cacatination
			} 

			// go to next level
			else if (c == '[') {
				pos = i + 1; // start of next substring

				String next = decodeString(s); // recursively form next repeat unit
				for (int n = 0; n < Integer.valueOf(num); n++) sb.append(next); // form poly-unit

				num = ""; // zero num to form next num 
				i = pos; // increment i to the position that is checked by recursion in base case
			} 

			// base case
			else if (c == ']'){
				pos = i; // incremental i to upper recursion level
				return sb.toString();
			}
		}

		return sb.toString();
    }

}

