class Solution {
	// Assumptions: nums is orted with no duplicates
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
       
        for (int i = 0; i < nums.length; i++) {
        	int num = nums[i];
        	while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
        		i++;
        	}
        	if (num != nums[i]) res.add(num + "->" + nums[i]);
        	else res.add(num + "");
        	
        }
        return res;
    }
    
    public List<String> summaryRanges(int[] nums) {
		List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        List<Integer> q = new LinkedList<>();
        q.add(nums[0]);
        q.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
        	if ((long) nums[i] == 1+ (long) nums[i - 1]) {
        		q.remove(q.size() - 1);
        		q.add(nums[i]);
        	} else {
        		if (q.get(0) == q.get(1)) {
        			list.add(Integer.toString(q.get(0)));
        		} else {
        			list.add(Integer.toString(q.get(0)) + "->" + Integer.toString(q.get(1)));
        		}
        		q.clear();
        		q.add(nums[i]);
        		q.add(nums[i]);
        	}
        }

        if (q.get(0).equals(q.get(1))) {
			list.add(Integer.toString(q.get(0)));
		} else {
			list.add(Integer.toString(q.get(0)) + "->" + Integer.toString(q.get(1)));
		}
        
        return list;
    }

}