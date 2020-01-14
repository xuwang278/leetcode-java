class Solution {
    // T: O(n)
    // S: O(1)
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum == target) return new int[] {lo + 1, hi + 1};
            else if (sum > target) hi--;
            else lo++;
        }
        return -1;
    }
}