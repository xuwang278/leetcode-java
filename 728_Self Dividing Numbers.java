class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++)
        	if (isValid(i)) ans.add(i);
        return ans;
    }

    private boolean isValid(int n) {
    	for (char c : String.valueOf(n).toCharArray()) {
    		if (c == '0' || n % (c - '0') != 0)
    			return false;
    	}
    	return true;
    }

    private boolean isValid2(int n) {
    	int num = n;
    	while (n > 0) {
    		int digit = n % 10;
    		if (digit == 0 || num % digit != 0) return false;
    		n /= 10;
    	} 
    	return true;
    }
}