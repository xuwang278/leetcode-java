class Solution {
	// 落单数的index一定是偶数
	// 寻找最小index, 使得nums[i] != nums[i*]
	// 其中, 若i是偶数, 则i* = i + 1; 若i是奇数, 则i* = i - 1, 
	public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length - 1; // 搜索空间: [lo, hi]
        while (lo + 1 < hi) {
        	int mid = lo + (hi - lo) / 2;
            // 如果mid是偶数, 那么如果它不是落单数, 与之相同的数一定数是mid+1
        	if (mid % 2 == 0) {
        		if (nums[mid] != nums[mid + 1]) hi = mid; 
        		else lo = mid; // 如果相同, 说明AABB模式在左侧没有破坏, 所以落单数一定在右侧
        	} 

            // 如果mid是奇数, 那么如果它不是落单数, 与之相同的数一定数是mid-1
            else {
        		if (nums[mid] != nums[mid - 1]) hi = mid;
        		else lo = mid; // 如果相同, 说明AABB模式在左侧没有破坏, 所以落单数一定在右侧
        	}
        }
        if (lo % 2 == 0) return nums[lo]; // 落单数index一定输偶数
        return nums[hi];
    }

    public int singleNonDuplicate(int[] nums) {
        // i could be 0, 2, 4, ... nums.length -1
        int lo = 0, hi = nums.length - 1; // 搜索空间: [lo, hi]
        while (lo + 1 < hi) {
        	int mid = lo + (hi - lo) / 2;
        	int n = mid ^ 1;
        	if (nums[mid] != nums[n]) hi = mid;
        	else lo = mid;
        }
        if (lo % 2 == 0) return nums[lo];
        return nums[hi];
    }





}