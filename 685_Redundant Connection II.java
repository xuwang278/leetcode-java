class Solution {
	// Solution 1: UF 
	// https://www.youtube.com/watch?v=lnmJT5b4NlM
	// https://www.youtube.com/watch?v=760I9vw7uA0

	// case 1: double parents
	// case 2: cycle
	// case 3: case 1 + case 2
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = new int[] {-1, -1};
        int[] can2 = new int[] {-1, -1};
        int[] root = new int[edges.length + 1];
        
        // check double parents
        for (int[] e : edges) {
            if (root[e[1]] == 0) root[e[1]] = e[0]; // first parent
            else { // 2nd parent
                can2 = new int[] {e[0], e[1]}; // latest parent -> e[1]
                can1 = new int[] {root[e[1]], e[1]}; // previous parent -> e[1]
                e[1] = 0; // remove double parents, if cycle still found then case 3
            }
        }
        
        // check cycle using UF
        for (int i = 0; i < root.length; i++) 
            root[i] = i;
        
        for (int[] e : edges) {
            if (e[1] == 0) continue; // ignore the edge with double parents
            if (find(root, e[0]) == e[1]) { // cycle
                if (can1[0] == -1) return e; // if not case 1, ie case 2
                return can1; // case 3
            }
            root[e[1]] = e[0]; // union
        }
        return can2;
    }
    
    private int find(int[] parents, int s) {
        if (parents[s] != s) parents[s] = find(parents, parents[s]);
        return parents[s];
    }

}