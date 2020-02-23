class Solution {
    public Node construct(int[][] g) { 
        return dfs(0, 0, g.length - 1, g.length - 1, g);
    }

    private Node dfs(int r1, int c1, int r2, int c2, int[][] g) {
        if (r1 > r2 || c1 > c2) return null;
        
        boolean isLeaf = true;
        int val = g[r1][c1];
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (g[i][j] != val) {
                    isLeaf = false;
                    break;
                }
            }    
        }
            
        if (isLeaf) return new Node(val == 1, true, null, null, null, null);
            
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
            dfs(r1, c1, rowMid, colMid, g),//top left 
            dfs(r1, colMid + 1, rowMid, c2, g),//top right
            dfs(rowMid + 1, c1, r2, colMid, g),//bottom left 
            dfs(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
    }
    
}