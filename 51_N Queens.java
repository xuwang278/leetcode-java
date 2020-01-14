class Solution {
	// http://zxi.mytechroad.com/blog/searching/leetcode-51-n-queens/ 
	// (1)每行一个皇后
	// (2)每列一个皇后
	// (3)正对角线一个皇后
	// (4)反对角线一个皇后
	private boolean[] col; // idx = y, |
    private boolean[] diag; // idx = x + y, 符合该关系的点都在同一对角线 / 上
    private boolean[] antiDiag; // idx = x - y + n - 1, , 符合该关系的点都在同一对角线 \ 上

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        diag = new boolean[2 * n - 1];
        antiDiag = new boolean[2 * n - 1];
        
        List<List<String>> res = new ArrayList<> ();
        dfs(res,new ArrayList<String>(), 0, n);
        return res;
    }

    // 一行一行选可以放皇后的位置
    // 一行只加一个Q, 所以条件(1)自然满足
    private void dfs(List<List<String>> res, List<String> list, int row, int n) {
    	// 加满n行
    	if (row == n){
            res.add(new ArrayList<>(list)); 
            return;
        }
        
        // 尝试把Q放到可用的下一列
        for (int i = 0; i < n; i++) {
        	// 别的皇后攻击范围
        	if (col[i] || diag[row + i] || antiDiag[row - i + n - 1]) continue;

        	char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);

            // 该皇后的攻击范围
            col[i] = true;
            diag[row + i] = true; 
            antiDiag[row - i + n - 1] = true;

            dfs(res, list, row + 1, n); // 走下一行
            
            // 解除该皇后的攻击范围
            list.remove(list.size() - 1);
            //charArray[i] = '.';
            col[i] = false;
            diag[row + i] = false; 
            antiDiag[row - i + n - 1] = false;
        }

    }
    
}