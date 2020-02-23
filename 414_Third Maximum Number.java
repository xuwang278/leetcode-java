class Solution {
	// T: O(n)
	// S: O(1)
    public int thirdMax(int[] nums) {
    	// min heap with 3 items
    	// the current 3rd largest item is on the top
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
        	if (set.contains(num)) continue;
        	pq.offer(num);
        	set.add(num);
        	if (pq.size() > 3) set.remove(pq.poll());
        }

        if (pq.size() < 3) {
        	while (pq.size() > 1) pq.poll();
        }

        return pq.peek();
    }

    // T: O(n)
	// S: O(1)
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        
        for (int n : nums) {
            if (max1 == n || max2 == n || max3 == n) continue;
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }
        
        return (int) (max3 == Long.MIN_VALUE ? max1 : max3);
    }
}