class Solution {
	
    // Solution 1:
    // https://www.cnblogs.com/grandyang/p/6181626.html
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int i = 0, j = 0, res = 0;
        for (int i = 0; i < houses.length; i++) {
        	int cur = houses[i];
            while (j < heaters.length - 1
                && Math.abs(heaters[j + 1] - cur) <= Math.abs(heaters[j] - cur)) {
                j++;
            }
            res = Math.max(res, Math.abs(heaters[j] - cur));
        }
        
        return res;
    }

    // Solution 2: Binary search
	// T: max(O(nlogn, mlogn))
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int ans = Integer.MIN_VALUE;

        for (int house : houses) {
        	int index = Arrays.binarySearch(heaters, house);
        	if (index < 0) index = -(index + 1);
        	int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
        	int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
        	ans = Math.max(ans, Math.min(dist1, dist2));
        }
        return ans;
    }
}