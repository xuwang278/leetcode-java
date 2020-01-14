class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) 
            return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();
        int n = words.length;
        int step = words[0].length();

        Map<String, Integer> map = new HashMap<>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        for (int i = 0; i < step; i++) {
            int lo = i, hi = i, cnt = 0;
            Map<String, Integer> curMap = new HashMap<>();
            while (hi + step < s.length()) {
                String r = s.substring(hi, hi + step);
                if (map.containsKey(r)) {
                    curMap.put(r, curMap.getOrDefault(r, 0) + 1);
                    if (curMap.get(r) <= map.get(r)) cnt++;

                    while (curMap.get(r) > map.get(r)) {
                        String l = s.substring(lo, lo + step);
                        curMap.put(l, curMap.get(l) - 1);
                        if (map.get(l) < map.get(l)) cnt--;
                        lo += step;
                    }

                    if (cnt == n) {
                        ans.add(lo);
                        String l = s.substring(lo, lo + step);
                        curMap.put(l, curMap.get(l) - 1);
                        lo += step;
                        cnt--;
                    }
                } else {
                    curMap.clear();
                    cnt = 0;
                    lo = hi + step;
                }

                hi += step;
            }

            curMap.clear();
        }

        return ans;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null s.length() == 0 || words == null || words.length == 0) 
            return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();
        int n = words.length;
        int step = words[0].length();

        Map<String, Integer> map = new HashMap<>();
        for (String w : words)
        	map.put(w, map.getOrDefault(w, 0) + 1);

        for (int i = 0; i <= s.length() - n * step; i++) {
        	Map<String, Integer> copy = new HashMap<>(map);
        	int cnt = n;
        	int j = i;
        	while (cnt > 0) {
        		String str = s.substring(j, j + step);
        		// str not in concatenation / str is extra
        		if (!copy.containsKey(str) || copy.get(str) < 1) 
        			break;

        		copy.put(str, copy.get(str) - 1); // reduce one
        		cnt--;
        		j += step;
        	}
        	if (cnt == 0) ans.add(i);
        }

        return ans;
    }

    

}