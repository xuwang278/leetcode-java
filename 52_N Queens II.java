class Solution {
	private boolean[] col; 
    private boolean[] diag; // idx = x + y, 符合该关系的点都在同一对角线上
    private boolean[] antiDiag; // idx = x - y + n - 1
    private int cnt;

    public int totalNQueens(int n) {
        col = new boolean[n];
        diag = new boolean[2 * n - 1];
        antiDiag = new boolean[2 * n - 1];
        cnt = 0;
        dfs(0, n);
        return cnt;
    }

    private void dfs(int row, int n) {
    	// 加满n行
    	if (row == n){
            cnt++;
            return;
        }

        // 每一列
        for (int i = 0; i < n; i++) {
        	// 别的皇后攻击范围
        	if (col[i] || diag[row + i] || antiDiag[row - i + n - 1]) continue;

            // 该皇后的攻击范围
            col[i] = true;
            diag[row + i] = true; 
            antiDiag[row - i + n - 1] = true;

            dfs(row + 1, n); 
            
    		// 解除攻击范围
            col[i] = false;
            diag[row + i] = false; 
            antiDiag[row - i + n - 1] = false;
        }

    }
}