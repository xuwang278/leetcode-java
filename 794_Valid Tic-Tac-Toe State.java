class Solution {
    // https://www.cnblogs.com/grandyang/p/9223105.html
    public boolean validTicTacToe(String[] board) {
        int turns = 0;
        boolean xwin = false;
        boolean owin = false;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag = 0;
        int antidiag = 0;

        // 将棋盘信息做统计
        for (int i = 0; i < 3; i++) {
        	for (int j = 0; j < 3; j++) {
        		if (board[i].charAt(j) == 'X') {
        			turns++;
        			rows[i]++;
        			cols[j]++;
        			if (i == j) diag++;
        			if (i + j == 2) antidiag++;
        		} else if (board[i].charAt(j) == 'O') {
        			turns--;
        			rows[i]--;
        			cols[j]--;
        			if (i == j) diag--;
        			if (i + j == 2) antidiag--;
        		}
        	}
        }

        xwin = rows[0] == 3 || rows[1] == 3 || rows[2] == 3 ||
               cols[0] == 3 || cols[1] == 3 || cols[2] == 3 ||
               diag == 3 || antidiag == 3;

        owin = rows[0] == -3 || rows[1] == -3 || rows[2] == -3 ||
               cols[0] == -3 || cols[1] == -3 || cols[2] == -3 ||
               diag == -3 || antidiag == -3;


        // x赢时必须领先一子 (turns == 1), o赢时双方子数相同(turns == 0)
       	if ((xwin && turns == 0) || (owin && turns == 1)) {
       		return false;
       	}

       	// X最多领先一子 (turns == 0, 1)且仅一方赢或者平
       	return (turns == 0 || turns == 1) && (!xwin || !owin);
    }
    
}