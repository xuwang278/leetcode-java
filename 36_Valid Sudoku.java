class Solution {
    // T: 9 * 9
    // S: O(1)
    public boolean isValidSudoku(char[][] board) {
        // 看每行中是否有重复元素
        for (int row = 0; row < 9; row++) {
            int[] map = new int[10];
            for (int i = 0; i < 9; i++) {
                char c = board[row][i];
                if (c != '.') {
                    if (map[c - '0'] > 0) return false;
                    else map[c - '0']++;
                }
            }
        }

        // 看每列中是否有重复元素
        for (int col = 0; col < 9; col++) {
            int[] map = new int[10];
            for (int i = 0; i < 9; i++) {
                char c = board[i][col];
                if (c != '.') {
                    if (map[c - '0'] > 0) return false;
                    else map[c - '0']++;
                }
            }
        }

        // 看每九宫格中是否有重复元素
        for (int box = 0; box < 9; box++) {
            int[] map = new int[10];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    char c = board[row + 3 * (box / 3)][col + 3 * (box % 3)];
                    if (c != '.') {
                        if (map[c - '0'] > 0) return false;
                        else map[c - '0']++;
                    }
                }
            }
        }

        return true;
    }

	// T: 9 * 9 (n^2)
	// S: O(n)
    // https://www.youtube.com/watch?v=4-SF0-p98NM
    public boolean isValidSudoku(char[][] board) {
    	for (int i = 0; i < board.length; i++) {
    		Set<Character> rows = new HashSet<>();
    		Set<Character> cols = new HashSet<>();
    		Set<Character> cube = new HashSet<>();
    		for (int j = 0; j < board[0].length; j++) {
                // 判断第i行
    			if (board[i][j] != '.' && !rows.add(board[i][j])) return false;
                // 判断第i列
    			if (board[j][i] != '.' && !cols.add(board[j][i])) return false;

    			int rowIndex = 3 * (i / 3);
    			int colIndex = 3 * (i % 3);

                // 从左到右, 从上到下, 判断小九宫格
    			if (board[rowIndex + j / 3][colIndex + j % 3] != '.' &&
    				!cube.add(board[rowIndex + j / 3][colIndex + j % 3]))
    				return false;
    		}
    	}
    	return true;
    }


    


}