class Solution {
    // shortest distance -> bfs
    // multi srcs (gates, 0) -> multi targets (rooms, INF)
    // -1 stops search
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        
        Queue<int[]> q = new LinkedList<>(); // support bfs
        int step = 0; // distance from nearest gate
        int[] dirs = new int[] {1, 0, -1, 0, 1}; // support expansion
        
        // load gates to q
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) q.offer(new int[] {i, j});
            }
        }
        
        // run bfs
        while (!q.isEmpty()) {
            int size = q.size();
            // all the nodes in the loop share the same step away from gate
            for (int s = 0; s < size; s++) {
                int[] top = q.poll();
                int r = top[0];
                int c = top[1];
 
                // expand
                for (int k = 0; k < 4; k++) {
                    int dr = dirs[k] + r;
                    int dc = dirs[k + 1] + c;
                    if (dr < 0 || dr >= rooms.length || dc < 0 || dc >= rooms[0].length 
                        || rooms[dr][dc] == -1) continue;
                    if (rooms[dr][dc] == Integer.MAX_VALUE) {
                        rooms[dr][dc] = step + 1;
                        q.offer(new int[] {dr, dc});
                    } 
                }
                
            }
            step++;
        }

    }

}