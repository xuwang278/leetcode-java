class Solution {

    // Solution 1:
    public int hammingDistance(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int bx = x % 2;
            int by = y % 2;
            if (bx != by) ans++;
            x >>= 1; // x /= 2
            y >>= 1;
        }
        return ans;
    }

    // Solution 2:
    public int hammingDistance(int x, int y) {
        int ans = 0;
        int t = x ^ y; // 相同为零，不同为一，avoid necessary interation (<= 31)
        while (t > 0) {
            ans += t & 1; // t % 2
            t >>= 1; // t /= 2
        }   
        return ans;
    }
}