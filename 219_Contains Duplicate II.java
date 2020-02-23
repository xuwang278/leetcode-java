class Solution {
    // Solution 1: Brute Force
    // T: O(n * k)
    // S: O(1)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < j - i <= k && nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // Solution 2: HashMap
    // T: O(n)
    // S: O(n)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>(); // nums[]
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(nums[i]); // previous index that nums[j] == duplicate 
            if (j != null && i - j <= k) return true;
            map.put(nums[i], i);

            // if (map.containsKey(nums[i])) {
            //     int j = map.get(nums[i]);
            //     if (i - j <= k) return true;
            // } 
            
            // map.put(nums[i], i);
            
        }
        return false;
    }

    // Solution 3: Sliding window
    // T: O(n)
    // S: O(k)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i - k - 1]); // update set 
            if(!set.add(nums[i])) return true; // any duplicate in it?
        }
        return false;
    }

}