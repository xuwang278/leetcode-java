class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++)
        	res.add(i ^ i >> 1); // 相同为0，不同为1; (i /= 2 vs. i >> 1)
        return res;
    }
}