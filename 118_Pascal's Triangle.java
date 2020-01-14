class Solution {
    // T: O(?)
    // S: O(?)
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> list = new ArrayList<>();
    	if (numRows <= 0) return list;
    	List<Integer> subList = new ArrayList<>();
    	for (int i = 0; i < numRows; i++) {
    		subList.add(0, 1);
    		for (int j = 1; j < subList.size() - 1; j++) {
    			subList.set(j, subList.get(j) + subList.get(j + 1));
    		}
    		list.add(new ArrayList(subList));
    		//subList.clear(); only expand subList by 1 entry per iteration
    	}
    	return list;
    }


    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> list = new ArrayList<>();
    	for (int i = 0; i < numRows; i++) {
    		List<Integer> subList = new ArrayList<>();
    		for (int j = 0; j < i + 1; j++) {
    			int num = 0;
    			if (j == 0 || j == i) num = 1;
    			else num = list.get(i - 1).get(j - 1) + list.get(i - 1).get(j);
    			subList.add(num);
    		}
    		list.add(subList);
    	}
    	return list;
    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        
        if (numRows < 1) return list;

        if (numRows == 1) {
        	List<Integer> subList = new ArrayList<>();
        	subList.add(1);
        	list.add(subList);
        	return list;
        }

        if (numRows == 2) {
        	List<Integer> subList1 = new ArrayList<>();
        	subList1.add(1);
        	List<Integer> subList2 = new ArrayList<>();
        	subList2.add(1);
        	subList2.add(1);
        	list.add(subList1);
        	list.add(subList2);
        	return list;
        }

        List<Integer> subList1 = new ArrayList<>();
        subList1.add(1);
        List<Integer> subList2 = new ArrayList<>();
        subList2.add(1);
        subList2.add(1);
        list.add(subList1);
        list.add(subList2);

        // starting 3rd row
        for (int i = 2; i < numRows; i++) {
        	List<Integer> subList = new ArrayList<>();
        	subList.add(0, 1);
        	// subList.add(i, 1); cause run-time error
        	List<Integer> pre = list.get(i - 1);
        	for (int j = 1; j < i; j++) {
        		subList.add(j, pre.get(j) + pre.get(j - 1));
       		}
       		subList.add(1);
        	list.add(subList);
        }
        return list;
    }

}