class Solution {
	// Solution 1:
	// T: O(n^4) n = words.length
	// S: O(1)
    public int maxProduct(String[] words) {
        if (words == null || words.length <= 1 || words[0].length() < 1) return 0;
        
    	int res = Integer.MIN_VALUE;
    	for (int i = 0; i < words.length; i++) {
    		for (int j = i + 1; j < words.length; j++)
    			res = Math.max(res, product(words[i], words[j]));
    	}
    	return res;
    }

    private int product(String s1, String s2) {
    	for (int i = 0; i < s1.length(); i++) {
    		for (int j = 0; j < s2.length(); j++)
    			if (s1.charAt(i) == s2.charAt(j)) return 0;
    	}
    	return s1.length() * s2.length();
    }

    // T: O(n^2)
    // S: O(n)
    // convert string to bit pattern
    // "a" - 1
    // "ace" - 10101
    // "az" - 10000000000000000000000001
    public int maxProduct(String[] words) {
    	int[] checker = new int[words.length];
    	int max = 0;
    	for (int i = 0; i < checker.length; i++) {
    		int num = 0;
    		for (int j = 0; j < words[i].length(); j++) {
    			num |= 1 << (words[i].charAt(j) - 'a');
    		}
    		checker[i] = num;
    	}

    	for (int i = 0; i < words.length; i++) {
    		for (int j = i + 1; j < words.length; j++) {
    			if (checker[i] & checker[j] == 0)
    				max = Math.max(max, words[i].length() * words[j].length());
    		}
    	}

    	return max;
    }
}