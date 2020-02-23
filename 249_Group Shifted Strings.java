class Solution {
	// 通过每个字符串的首字母 算出偏移量 然后将其还原为a开头的 作为key
	// 拥有相同key的字符串是一组
    public List<List<String>> groupStrings(String[] strings) {
    	List<List<String>> ans = new ArrayList<>();
    	Map<String, List<String>> map = new HashMap<>();
    	for (String s : strings) {
    		int offset = s.charAt(0) - 'a';
    		String key = "";
    		for (int i = 0; i < s.length(); i++) {
    			char c = (char) (s.charAt(i) - offset);
    			if (c < 'a') c += 26;
    			// char c = (char) ((s.charAt(i) - offset + 26) % 26);
    			key += c;
    		}
    		
    		map.putIfAbsent(key, new ArrayList<>());
    		map.get(key).add(s);
    	}

    	for (String key : map.keySet()) {
    		List<String> list = map.get(key);
    		ans.add(list);
    	}

    	return ans;
    }
}