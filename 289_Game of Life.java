class Solution {
	
    // https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
    // T: O(mn)
	// S: O(1)
    public void gameOfLife(int[][] board) {
    	if (board == null || board.length == 0 || board[0].length == 0) return;

    	int ROW = board.length, COL = board[0].length;
    	for (int i = 0; i < ROW; i++) {
    		for (int j = 0; j < COL; j++) {
    			int lives = liveNeighbors(board, ROW, COL, i, j);

	            // rule 1: live -> die
	            if (board[i][j] == 1 && lives < 2) continue; // 0b01

	            // rule 2: live -> live
	            else if (board[i][j] == 1 && (lives == 2 || lives == 3)) {
	            	board[i][j] = 0b11; 
	            }

	            // rule 3: live -> die
	            else if (board[i][j] == 1 && lives > 3) continue; // 0b01

	            // rule 4: die -> live
	            else if (board[i][j] == 0 && lives == 3) {
	                board[i][j] = 0b10; // Make the 2nd bit 1: 0b00 ---> 0b10
	            }
	        }
    	}

    	// erase the last bit to get next generation
    	for (int i = 0; i < ROW; i++) {
    		for (int j = 0; j < COL; j++) {
    			board[i][j] >>= 1; // reserve 2nd last bit
    		}
    	}
    }

    // scan 3*3 with board[i][j] as center
    // count how may cells whose last bit is 1 (live currently)
    private int liveNeighbors(int[][] board, int ROW, int COL, int i, int j) {
    	int lives = 0;
    	for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, ROW - 1); x++) {
    		for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, COL - 1); y++) {
    			lives += board[x][y] & 0b01;
    		}
    	}
    	return lives - (board[i][j] & 0b01); // exclude itself
    }

    // Other solutions：
    // https://segmentfault.com/a/1190000003819277
    // https://www.youtube.com/watch?v=juGxbF-eadU

}