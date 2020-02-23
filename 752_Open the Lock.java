class Solution {
    // T: O(8*10,000) from 0000 to 9999 there are 10,000 nodes, each node has expanded to 8 neighbors
    // S: O(10,000 + deadends)
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String d : deadends) deads.add(d);
        String start = "0000";
        if (deads.contains(start)) return -1;
        
        
        Set<String> visited = new HashSet();
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                String top = q.poll();
                for (int i = 0; i < 4; i++) { // modify each digit
                    for (int j = -1; j < 2; j += 2) { // expand: 2 dir
                        char[] chars = top.toCharArray(); // modify one digit at each time while keeping other unchanged
                        chars[i] += j;
                        if (chars[i] - '0' > 9) chars[i] = '0';
                        if (chars[i] - '0' < 0) chars[i] = '9';
                        String next = new String(chars);
                        if (next.equals(target)) return ++step; // step + 1 works BUT NOT step++, which actually returns step and then plus 1 
                        if (deads.contains(next)) continue; // not on SP
                        if (visited.contains(next)) continue; // already on q (SP)
                        visited.add(next);
                        q.offer(next); // put on SP
                    }
                }
            }
            step++;
        }
        return -1;
    }
}