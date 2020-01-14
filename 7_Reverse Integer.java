class Solution {
	// Time: O(n)
    // Space: O(1)
    public int reverse(int x) {
        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        long ans = 0;
        while (x != 0) { // while (x > 0) 
            ans = ans * 10 + x % 10;
            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return sign * ans;
    }

    // -123 % 10 = -3
}