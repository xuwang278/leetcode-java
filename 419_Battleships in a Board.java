class Solution {
    // Solution 1:
    // T: O(row * col)
    // S: O(max(row, col))
	public int countBattleships(char[][] board) {
        // assume board is valid
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    dfs(board, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    private void dfs(char[][] board, int i, int j) {
        board[i][j] = '.';
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length 
                || board[di][dj] == '.' ) continue;
            dfs(board, di, dj);
        }
    }

	// Solution 2:
    // T: O(row * col)
    // S: O(1)
    public int countBattleships(char[][] board) {
        // assume board is valid
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if (board[i][j] == 'X') {
        			if (i > 0 && board[i - 1][j] == 'X') continue; // up (still counting the same ship)
        			if (j > 0 && board[i][j - 1] == 'X') continue; // left (still counting the same ship)
        			cnt++; // find the first 'X' of a ship
        		}
        	}
        }
        return cnt;
    }
}