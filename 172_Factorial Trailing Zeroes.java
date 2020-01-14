class Solution {
    // out of range
    public int trailingZeroes(int n) {
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i;
        }
        
        long res = f[n];
        int count = 0;
        while(res > 0) {
            if (res % 10 != 0) break;
            count++;
            res /= 10;
        }
        return count;
        
    }

    public int trailingZeroes(int n) {
        if (n < 5) return 0;
        if (n < 10) return 1;
        return n / 5 + trailingZeroes(n / 5);
    }

    public int trailingZeroes(int n) { 
        int res = 0;
        while (n > 0) {
            res += n / 5;
            n /= 5;
        }
        return res;
    }

}