class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        for (int i = 0; i < flowerbed.length && cnt < n; i++) {
        	if (flowerbed[i] == 0) {
        		int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
        		int prev = (i == 0) ? 0 : flowerbed[i - 1];
        		if (next == 0 && prev == 0) {
        			flowerbed[i] = 1;
        			cnt++;
        		}
        	}
        }
        return cnt == n;
    }
}