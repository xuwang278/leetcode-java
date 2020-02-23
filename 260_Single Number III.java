class Solution {
    public int[] singleNumber(int[] nums) {
        
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
        	if (set.contains(n)) {
        		set.remove(n);
        	} else {
        		set.add(n);
        	}
        }

        int[] res = new int[set.size()];
        int i = 0;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
        	res[i++] = iterator.next();
        }

        return res;
    }

    public int[] singleNumber(int[] nums) {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;
        
        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
}