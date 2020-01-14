public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int mask = 1;
        int s = n, c, res = 0;
        for (int i = 0; i < 32; i++) {
            c = s & mask;
            res <<= 1;
            res += c;
            s >>= 1;
        }
        return res;
    }
}