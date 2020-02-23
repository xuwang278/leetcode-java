class Solution {
    // T: O(n)
    // S: O(n)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        // nums = {1...n}
        int[] cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) cnt[i] = 1;
        for (int num : nums) cnt[num]--;
        // if appear, -1
        // what's remain 1 is the disppearing item 
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            if (cnt[i] == 1) ans.add(i);
        
        return ans;
    }

    // https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
    // T: O(n)
    // S: O(1)
    For each number i in nums, we mark the number that i points as negative.
    Then we filter the list, get all the indexes # who points to a positive number

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) nums[idx] *= -1;
        }
        
        for(int i = 0; i < nums.length; i++) 
            if(nums[i] > 0) ans.add(i + 1); // not referred by (i + 1) 
            
        return ans;
    }
}