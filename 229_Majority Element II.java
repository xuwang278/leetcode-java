class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return new ArrayList<Integer>();
        }

        // at most two candidates
        int candidateA = nums[0]; // dummy candidate
        int candidateB = nums[0]; // dummy caididate
        int countA = 0;
        int countB = 0;

        for (int num : nums) {
        	if (num == candidateA) countA++;
        	else if (num == candidateB) countB++;
        	else if (countA == 0) {
        		candidateA = num;
        		countA++;
        	} else if (countB == 0) {
        		candidateB = num;
        		countB++;
        	} else {
        		countA--;
        		countB--;
        	}
        }

        countA = 0;
        countB = 0;
        for (int num : nums) {
        	if (num == candidateA) countA++;
        	else if (num == candidateB) countB++; // else if!!
        }

        List<Integer> list = new ArrayList<>();
        if (countA > nums.length / 3) list.add(candidateA);
        if (countB > nums.length / 3) list.add(candidateB);
        return list;

    }
}