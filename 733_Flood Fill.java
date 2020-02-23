class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, image[sr][sc], newColor);
        // bfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || 
            image[i][j] != oldColor || image[i][j] == newColor)
            return;
        image[i][j] = newColor;
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; k++) 
            dfs(image, i + dirs[k], j + dirs[k + 1], oldColor, newColor);
    }

    private void bfs(int[][] image, int i, int j, int oldColor, int newColor) {
        Queue<int[]> q = new LinkedList<>();
        image[i][j] = newColor; // mark and offer
        q.offer(new int[] {i, j});
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] top = q.poll();
            for (int k = 0; k < 4; k++) {
                int di = top[0] + dirs[k];
                int dj = top[1] + dirs[k + 1];
                if (di < 0 || di >= image.length || dj < 0 || dj >= image[0].length || 
                    image[di][dj] != oldColor || image[di][dj] == newColor)
                    continue;
                image[di][dj] = newColor; // mark and offer
                q.offer(new int[] {di, dj});
            }
        }
    }

}