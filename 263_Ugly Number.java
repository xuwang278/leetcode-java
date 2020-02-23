class Solution {
	// T: O(log2(n) + log3(n) + log5(n))
	// S: O(1)
	// ugly number: num = 2^x3^y5^z
	public boolean isUgly(int num) {
		if (num == 0) return false;
		int[] factors = {2, 3, 5};
		for (int factor : factors) // 除尽每一个因子
			while (num % factor == 0) num /= factor;
		return num == 1; // if num != 1, num contains other factors than 2,3,5
		
	}
	
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        if (num % 2 == 0) return isUgly(num / 2);
        if (num % 3 == 0) return isUgly(num / 3);
        if (num % 5 == 0) return isUgly(num / 5);
        return false;
    }

}