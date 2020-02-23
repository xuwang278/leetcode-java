class Solution {
    // T: O(n)
    // S: O(n)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.add(n)) return true;
        }
        return false;
    }

    // T: O(nlogn)
    // S: heap sort: O(1), quicksort: O(logn) 
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums); // bring duplicates together
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

}