class Solution {
	// T: O(n)
	// S: O(n)
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);   
        }
        
        for (int n : map.keySet()) {
            if (map.get(n) == 1) return n;
        }
        return -1;
    }

    // 抵消法 （抵消出现两次的两个数）
    // T: O(n)
	// S: O(1)
    // 相同为零，不同为一
	// 1^1 = 0, 0^0 = 0, 1^0 = 1 （不进位的加法）
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int n : nums) x = x ^ n; //As xoring same numbers it remains same atlast one unique value is returned.
        return x;
   	}

    100, 001, 010, 001, 010
000
    100, 101, 111, 110, 100
}