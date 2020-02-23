class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>(); // unique num in nums1
        for (int n : nums1) set.add(n);
        
        List<Integer> list = new ArrayList<>();
        for (int n : nums2) {
            if (set.contains(n)) {
                list.add(n); // intersected num
                set.remove(n); 
            }
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = list.get(i);
        return ans;
        
    }

    public int[] intersection(int[] nums1, int[] nums2) {
    	Set<Integer> set = new HashSet<>();
    	Set<Integer> intersect = new HashSet<>();
    	for (int n : nums1) set.add(n);
    	for (int m : nums2) {
    		if (set.contains(m)) intersect.add(m);
    	}
        int[] res = new int[intersect.size()];
        int i = 0;
        for (int x : intersect) res[i++] = x;
        return res;
    }
}