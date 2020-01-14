class Solution {
    // T: O(mn)
    // S: O(1)?
    private static class Pair {
        int row;
        int col;

        public Pair(int i, int j) {
            row = i;
            col = j;
        }
    }

    // Solution 1: BFS
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        // 'O' that are connected to the 4 borders will survive; find those 'O' using BFS/DFS 
        // and label them as '#'.
        // Then rewrite '#' to 'O', and other 'O' to 'X'

        // fill 1st and last row
        for (int i = 0; i < board[0].length; i++) {
            bfs(board, 0, i);
            bfs(board, board.length - 1, i);
        }

        // fill 1st and last col
        for (int i = 0; i < board.length; i++) {
            bfs(board, i, 0);
            bfs(board, i, board[0].length - 1);
        }

        // turn survived 'O' (now labeled as '#') to 'O';
        // other 'O' to 'X'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }

    }

    private void bfs(char[][] board, int i, int j) {
        if (board[i][j] != 'O') return;
        board[i][j] = '#';
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int row = pair.row;
            int col = pair.col;

            // if above is 'O'
            // PAY ATTENTION to >= 0
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                q.offer(new Pair(row - 1, col));
                board[row - 1][col] = '#';
            }
    
            // below 
            if (row + 1 < board.length && board[row + 1][col] == 'O') {
                q.offer(new Pair(row + 1, col));
                board[row + 1][col] = '#';
            }

            // left 
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                q.offer(new Pair(row, col - 1));
                board[row][col - 1] = '#';
            }

            // right
            if (col + 1 < board[0].length - 1 && board[row][col + 1] == 'O') {
                q.offer(new Pair(row, col + 1));
                board[row][col + 1] = '#';
            }
        }
    }

    // Solution 1' 
    // BFS with memorization
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        boolean[][] visited = new boolean[board.length][board[0].length];

        // fill 1st and last row
        for (int i = 0; i < board[0].length; i++) {
            bfs(board, 0, i, visited);
            bfs(board, board.length - 1, i, visited);
        }

        // fill 1st and last col
        for (int i = 0; i < board.length; i++) {
            bfs(board, i, 0, visited);
            bfs(board, i, board[0].length - 1, visited);
        }

        // turn survived 'O' (now labeled as '#') to 'O';
        // other 'O' to 'X'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }

    }

    // BFS starting from board[i][j]
    private void bfs(char[][] board, int i, int j, boolean[][] visited) {
        if (visited[i][j]) return;
        if (board[i][j] != 'O') {
            visited[i][j] = true;
            return;
        }
        // change 'O' to '#'
        board[i][j] = '#';
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int row = pair.row;
            int col = pair.col;

            if (visited[row][col]) continue;
            visited[row][col] = true;
            
            // if above is 'O' 
            if (row - 1 > 0 && board[row - 1][col] == 'O') {
                q.offer(new Pair(row - 1, col));
                board[row - 1][col] = '#';
            }
    
            // below 
            if (row + 1 < board.length && board[row + 1][col] == 'O') {
                q.offer(new Pair(row + 1, col));
                board[row + 1][col] = '#';
            }

            // left
            if (col - 1 > 0 && board[row][col - 1] == 'O') {
                q.offer(new Pair(row, col - 1));
                board[row][col - 1] = '#';
            }

            // right
            if (col + 1 < board[0].length - 1 && board[row][col + 1] == 'O') {
                q.offer(new Pair(row, col + 1));
                board[row][col + 1] = '#';
            }
        }
    }

    // Solution 2: DFS
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        // fill 1st and last row
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        // fill 1st and last col
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }

        // turn survived 'O' (now labeled as '#') to 'O';
        // other 'O' to 'X'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }

    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length -1 || board[i][j] == 'X' || board[i][j] == '#') return;
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
    
    // Solution 2': DFS with memorization
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        boolean[][] visited = new boolean[board.length][board[0].length];

        // fill 1st and last row
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i, visited);
            dfs(board, board.length - 1, i, visited);
        }

        // fill 1st and last col
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0, visited);
            dfs(board, i, board[0].length - 1, visited);
        }

        // turn survived 'O' (now labeled as '#') to 'O';
        // other 'O' to 'X'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }

    }

    private void dfs(char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length -1 || visited[i][j] || board[i][j] == 'X' || board[i][j] == '#') 
            return;
        board[i][j] = '#';
        visited[][] = true;
        dfs(board, i - 1, j, visited);
        dfs(board, i + 1, j, visited);
        dfs(board, i, j - 1, visited);
        dfs(board, i, j + 1, visited);
    }
}