class Solution {
	// T: O(klogm), m - # of unique chars in s
	// S: O(1)
    public String rearrangeString(String s, int k) {
        if (k == 0 || s.length() < k)  return s;

        int[] map = new int[26];
        for (char c : s.toCharArray()) {
        	map[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        // sort by freq; otherwise a come first than b
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
        	@Override
        	public int compare(int[] a, int[] b) {
        		if (a[1] == b[1]) return a[0] - b[0];
        		return b[1] - a[1];
        	}
        });

        for (int i = 0; i < 26; i++) {
        	if (map[i] > 0) {
        		pq.offer(new int[] {i, map[i]}); // [char - 'a', freq]
        	}
        }

        while (!pq.isEmpty()) {
        	List<Integer> list = new ArrayList<>();
        	// try to put k distinct chars to sb
        	for (int i = 0; i < k; i++) {
        		int[] top = pq.poll();
        		sb.append((char) (top[0] + 'a'));
        		list.add(top[0]);

        		// if there are less than k items in pq
        		if (pq.isEmpty()) {
        			// 最后一位pq为空是正常的; 或者最后长度满足条件了,最后一次无需加满k个数
        			// 除此之外, 都无法得到合法结果, return ""
        			if (i != k - 1 && sb.length() != s.length())
        				return "";
        			break;
        		}
        	}

        	for (int i : list) {
        		if (--map[i] > 0) {
        			pq.offer(new int[] {i, map[i]});
        		}
        	}
        }

        return sb.toString();
    }
}