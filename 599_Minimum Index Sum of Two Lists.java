class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
    	Map<String, Integer> map = new HashMap<>(); // String -> idx
    	List<String> list = new ArrayList<>();
    	int min = Integer.MAX_VALUE;
    	for (int i = 0; i < list1.length; i++) 
    		map.put(list1[i], i);
    	for (int i = 0; i < list2.length; i++) {
    		Integer j = map.get(list2[i]);
    		if (j != null && i + j <= min) {
    			if (i + j < min) {
    				min = i + j;
    				list.clear();
    			}
    			list.add(list2[i]);
    		}
    	}
    	return list.toArray(new String[list.size()]);
    }
}