class Solution {
	public int[] constructRectangle(int area) {
    	int a = (int) Math.sqrt(area);
    	while (area % a != 0) a--;
    	int b = area / a;
    	return new int[]{b, a};
    }
}

