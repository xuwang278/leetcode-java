class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }

    private boolean solve(char[][] board) {
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j] == '.') {
    				for (char c = '1'; c <= '9'; c++) {
    					if (isValid(board, i, j, c)) {
    						board[i][j] = c;
    						if (solve(board)) return true;
    						else board[i][j] = '.';
    					}
    				}
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    // 依次检查row行, col列, row,col所在小方格里, 全部位置, 是否有c存在
    // 若存在, 非法
    private boolean isValid(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.' && board[row][i] == ch) return false;
            if (board[i][col] != '.' && board[i][col] == ch) return false;
            int r = 3 * (row / 3) + i / 3;
            int c = 3 * (col / 3) + i % 3;
            if (board[r][c] != '.' && board[r][c] == ch) return false;
        }
        return true;
    }
}
