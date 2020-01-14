class Solution {
	// T: O(m * nlogn) (strs.length() * sort)
	// S: O(n) (HashMap)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0) return ans;
        
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        
        for (List<String> list : map.values())
            ans.add(list);
        
        return ans;
        
    }

}


