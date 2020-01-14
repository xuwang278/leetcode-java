class Solution {
    // T: O(n^2)
    // S: O(n)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        
        Arrays.sort(nums); // O(nlogn)
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[lo] + nums[hi] == -nums[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[lo]);
                    list.add(nums[hi]);
                    ans.add(list);
                    lo++;
                    hi--;
                    while (lo < hi && nums[lo] == nums[lo - 1]) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == nums[hi + 1]) {
                        hi--;
                    }
                } else if (nums[lo] + nums[hi] < -nums[i]) lo++;
                else hi--;
            }
        }
        
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();
        
        Set<List<Integer>> ans = new HashSet<>();
        Arrays.sort(nums); // crucial
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum == -nums[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[lo++]);
                    list.add(nums[hi--]);
                    ans.add(list);
                } else if (sum < -nums[i])
                    lo++;
                else
                    hi--;
            }
        }
        return new ArrayList<>(ans);
    }
    
}