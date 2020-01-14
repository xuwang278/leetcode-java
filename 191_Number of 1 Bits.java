public class Solution {
    // you need to treat n as an unsigned value

    11 -> 1011

    1011
  & 0001 （同为1得1，其他得0）
  = 0001 res = 1

    0101
  & 0001
  = 0001 res = 2

    0010
  & 0001
  = 0000 res = 2

    0001
  & 0001
  = 0000 res = 3

    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            n >>= 1;
        }
        return res;
    }


    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count += 1;
        }
        return count;
    }    
    
}