class Solution {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length < 2) return true;
        
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
            	cnt++;
                if (cnt > 1) return false;
            	if (i - 2 < 0 || nums[i - 2] <= nums[i]) 
            		nums[i - 1] = nums[i]; // case 1: 4改成2
            	else nums[i] = nums[i - 1]; // case 2: 2改成4
            }
        }
        
        return true;
    }
}

case 1： 2 4 2 6
case 2： 3 4 2 6