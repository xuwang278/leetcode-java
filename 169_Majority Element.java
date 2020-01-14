class Solution {
    // Solution 1: HashMap (unordered table)
    // T: O(n)
    // S: O(n)
    public int majorityElement(int[] nums) {
        // Key: num -> value: times
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, map.get(n) + 1);
            }
            if (map.get(n) > nums.length / 2) return n;
        }
        return -1;
    }

    // Solution 2: Sort
    // T: O(nlogn) 
    // S: O(1)
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Solution 3: Boyer-Moore Vote
    // assume that the array is non-empty and the majority element always exist in the array
    // T: O(n)
    // S: O(1)
    public int majorityElement(int[] nums) {
        Integer majority = null;
        int count = 0;
        for (int n : nums) {
            if (count == 0) {
                majority = n;
                count++;
            } else if (majority == n) count++;
            else count--;
        }
        return majority;
    }

    // Solution 3': Boyer-Moore Vote
    // majority may not exsit
    public Integer majorityElement(int[] nums) {
        Integer majority = null;
        int count = 0;
        for (int n : nums) {
            if (count == 0) {
                majority = n;
                count++;
            } else if (majority == n) count++;
            else count--;
        }

        if (count == 0) return null;

        count = 0;
        for (int n : nums) {
            if (n == majority)
                count++;
        }
        return count > nums.length / 2 ? majority : null;
    }

}