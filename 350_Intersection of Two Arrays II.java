class Solution {
    // T: O(m + n)
    // S: O(m)
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // num -> freq
        for (int n : nums1)
            map.put(n, map.getOrDefault(n, 0) + 1);
        
        List<Integer> list = new ArrayList<>();
        for (int n : nums2) {
            if (map.containsKey(n)) {
                list.add(n); // find a intersected num
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) map.remove(n);
            }
        }
        
        // convert list to array
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = list.get(i);
        
        return ans; 
    }

    // T: O(nlgn + n)
    // S: O(1)
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) j++;
            else i++;
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = list.get(i);
        
        return ans;

    }

}