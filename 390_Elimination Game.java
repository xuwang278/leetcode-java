class Solution {

	// Solution 1: Simuli, TLE
    public int lastRemaining(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) list.add(i);
		boolean fromLeftToRight = true;
		
		while (list.size() > 1) {
			List<Integer> copy = new ArrayList<>(list);
			if (fromLeftToRight) {
				for (int i = 0; i < list.size(); i += 2) {
					copy.remove((Integer) list.get(i));
				}
			} else {
				for (int i = list.size() - 1; i >= 0; i -= 2) {
					copy.remove((Integer) list.get(i));
				}
			}
			
			list = copy;
			fromLeftToRight = !fromLeftToRight;
		}
		
		return list.get(0);
	}

// https://leetcode.com/problems/elimination-game/discuss/87119/JAVA%3A-Easiest-solution-O(logN)-with-explanation
    public int lastRemaining(int n) {
        boolean left = true;
        int remaining = n;
        int step = 1;
        int head = 1;
        while (remaining > 1) {
            if (left || remaining % 2 == 1) {
                head = head + step;
            }
            remaining = remaining / 2;
            step = step * 2;
            left = !left;
        }
        return head;
    }
}

