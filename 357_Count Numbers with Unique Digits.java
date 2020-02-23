
class Solution {
	// https://www.youtube.com/watch?v=UVEr0cLjrA0
    public int countNumbersWithUniqueDigits(int n) {
    	if (n < 0) return 0;
    	if (n == 0) return 1;
    	long res = 1; // 0

    	// each digit
    	for (int i = 1; i <= Math.min(n, 10); i++) {
    		res += 9 * A(i - 1, 9); // 第一位非零，后i-1位从9个数里选
    	}
    	return res;
    }

    private int A(int i, int j) {
    	int res = 1;
    	for (int k = j; k > j - i; k--)
    		res *= k;
    	return res;
    }
}