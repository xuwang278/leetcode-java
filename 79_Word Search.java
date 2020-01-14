class Solution {
	// T: O(4^L) L = word length
	// S: O(m*n + L) 输入空间 + 递归深度
    // own version
    public boolean exist(char[][] board, String word) {
        // assume board and words are valid input
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, int idx, String target) {
        // pre-condition: i,j are valid and has not visited
        char c = board[i][j];
        if (c != target.charAt(idx)) return false;
        if (idx == target.length() - 1) return true;
        
        // if c matches target[idx], expand in 4 directions, to see if 
        // the following chars match
        board[i][j] = '#';
        int[][] dirs = new int[][] {{-1 ,0}, {1, 0}, {0, -1}, {0, 1}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length || board[di][dj] == '#') continue;
            // di, dj are valid and has not visited
            if (dfs(board, di, dj, idx + 1, target)) return true;
        }
        board[i][j] = c;
        return false;
    }
    


    // another version
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false; 
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) return true; // strat from every position
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) return true; // idx越界, 放句首
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false; // i,j越界
        }
        if (board[i][j] != word.charAt(idx)) return false; // false case
        
        char cur = board[i][j];
        board[i][j] = '#'; // visited
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; k++) { // expand
            // once there is a successful path, return true;
            if (dfs(board, word, i + dirs[k], j + dirs[k + 1], idx + 1)) {
                // board[i][j] = cur; 
                return true;
            }
        }
        board[i][j] = cur; // restore and can be used for other paths
        return false; // no such path
    }


}