class TicTacToe {

    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;
    private int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        this.n = n;
    }
    
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1; // player 1 -> 1, player 2 -> -1
        rows[row] += toAdd; // contribute to row
        cols[col] += toAdd; // contribute to col
        if (row == col) diagonal += toAdd; // contribute to dia
        if (row + col == n - 1) antiDiagonal += toAdd; // contribute to anti-dia
        
        // occupy entire col/row/dia/anti-dia
        if (Math.abs(rows[row]) == n ||
            Math.abs(cols[col]) == n ||
            Math.abs(diagonal) == n ||
            Math.abs(antiDiagonal) == n)
            return player;
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */