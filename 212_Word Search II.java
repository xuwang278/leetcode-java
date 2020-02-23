// latest version: using trieWithDelete (LC#208)

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieWithDelete trie = new TrieWithDelete();
        for (String w : words) {
            trie.insert(w);
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, new StringBuilder(), trie, ans);
            }
        }
        
        return ans;
    }
    
    // dfs version 1 
    private void dfs(char[][] board, int i, int j, StringBuilder sb, TrieWithDelete trie, List<String> ans) {
        // pre-condition: i,j and board[i][j] are valid
        char c = board[i][j];
        sb.append(c);
        String next = sb.toString();
        if (!trie.startsWith(next)) return; // early stop
        if (trie.search(next)) {
            ans.add(next);
            trie.delete(next); // delete to avoid duplicates
        }
        
        board[i][j] = '#';
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length || board[di][dj] == '#') continue;
            dfs(board, di, dj, sb, trie, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        board[i][j] = c;
    }

    // dfs version 2 (check boundary as base case)
    private void dfs(char[][] board, int i, int j, StringBuilder sb, TrieWithDelete trie, List<String> ans) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') {
            sb.append('#'); // 即便越界或已访问, 也要吃一个'#'进sb, 因为return后一定要吐一个(backtracking)
            return;
        }
        
        char c = board[i][j];
        sb.append(c);
        String next = sb.toString();
        if (!trie.startsWith(next)) return; // early stop
        if (trie.search(next)) {
            ans.add(next);
            trie.delete(next); // delete to avoid duplicates
        }
        
        board[i][j] = '#';
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            //if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length || board[di][dj] == '#') continue;
            dfs(board, di, dj, sb, trie, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        board[i][j] = c;
    }
    
    // implementation of trie with delete function
    public class TrieWithDelete {

        private class TrieNode {
            Map<Character, TrieNode> children;
            boolean endOfWord;

            public TrieNode() {
                children = new HashMap<>();
                endOfWord = false;
            }
        }

        private final TrieNode root;

        public TrieWithDelete() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = p.children.get(ch);
                if (node == null) {
                    node = new TrieNode();
                    p.children.put(ch, node);
                }
                p = node;
            }

            p.endOfWord = true;
        }

        public void insertRecursive(String word) {
            insertRecursive(root, word, 0);
        }

        private void insertRecursive(TrieNode p, String word, int idx) {
            if (idx == word.length()) {
                p.endOfWord = true;
                return;
            }

            char ch = word.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                p.children.put(ch, node);
            }

            insertRecursive(node, word, idx + 1);
        }
    
        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = p.children.get(ch);
                if (node == null) {
                    return false;
                }
                p = node;
            }
            return p.endOfWord;
        }
    
        public boolean searchRecursive(String word) {
            return searchRecursive(root, word, 0);
        }
    
        private boolean searchRecursive(TrieNode p, String word, int idx) {
            if (idx == word.length()) {
                return p.endOfWord;
            }

            char ch = word.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                return false;
            }
            return searchRecursive(node, word, idx + 1);
        }
    
        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                TrieNode node = p.children.get(ch);
                if (node == null) {
                    return false;
                }
                p = node;
            }
            return true;
        }

        public boolean startsWithRecursive(String prefix) {
            return startsWithRecursive(root, prefix, 0);
        }

        private boolean startsWithRecursive(TrieNode p, String prefix, int idx) {
            if (idx == prefix.length()) {
                return true;
            }

            char ch = prefix.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                return false;
            }
            return startsWithRecursive(node, prefix, idx + 1);
        }

        public void delete(String word) {
            delete(root, word, 0);
        }
        
        /**
         * Returns true if parent should delete the mapping
         */
        private boolean delete(TrieNode p, String word, int idx) {
            if (idx == word.length()) {
                if (!p.endOfWord) {
                    return false;
                }
                p.endOfWord = false;
                return p.children.size() == 0;
            }

            char ch = word.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                return false;
            }

            // continue tracking p's child node and also return information
            // on if or not to delete the calling node
            boolean shouldDeleteNode = delete(node, word, idx + 1);

            if (shouldDeleteNode) {
                p.children.remove(ch);
                return p.children.size() == 0;
            }

            return false;
        }

    }
}

class Solution {
    // T: O(len * 4^L) L = average length of word in words, len = # of word
    // S: O(m*n + L) 输入空间 + 递归深度
    public List<String> findWords(char[][] board, String[] words) {
        // assume board and words are valid inputs
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        
        Set<String> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
        class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieWithDelete trie = new TrieWithDelete();
        for (String w : words) {
            trie.insert(w);
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, new StringBuilder(), trie, ans);
            }
        }
        
        return ans;
    }
    
    private void dfs(char[][] board, int i, int j, StringBuilder sb, TrieWithDelete trie, List<String> ans) {
        char c = board[i][j];
        sb.append(c);
        String next = sb.toString();
        if (!trie.startsWith(next)) return; // early stop
        if (trie.search(next)) {
            ans.add(next);
            trie.delete(next); // delete to avoid duplicates
        }
        
        board[i][j] = '#';
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length || board[di][dj] == '#') continue;
            dfs(board, di, dj, sb, trie, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        board[i][j] = c;
    }
    
    public class TrieWithDelete {

        private class TrieNode {
            Map<Character, TrieNode> children;
            boolean endOfWord;

            public TrieNode() {
                children = new HashMap<>();
                endOfWord = false;
            }
        }

        private final TrieNode root;

        public TrieWithDelete() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = p.children.get(ch);
                if (node == null) {
                    node = new TrieNode();
                    p.children.put(ch, node);
                }
                p = node;
            }

            p.endOfWord = true;
        }

        public void insertRecursive(String word) {
            insertRecursive(root, word, 0);
        }

        private void insertRecursive(TrieNode p, String word, int idx) {
            if (idx == word.length()) {
                p.endOfWord = true;
                return;
            }

            char ch = word.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                p.children.put(ch, node);
            }

            insertRecursive(node, word, idx + 1);
        }
    
        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = p.children.get(ch);
                if (node == null) {
                    return false;
                }
                p = node;
            }
            return p.endOfWord;
        }
    
        public boolean searchRecursive(String word) {
            return searchRecursive(root, word, 0);
        }
    
        private boolean searchRecursive(TrieNode p, String word, int idx) {
            if (idx == word.length()) {
                return p.endOfWord;
            }

            char ch = word.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                return false;
            }
            return searchRecursive(node, word, idx + 1);
        }
    
        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                TrieNode node = p.children.get(ch);
                if (node == null) {
                    return false;
                }
                p = node;
            }
            return true;
        }

        public boolean startsWithRecursive(String prefix) {
            return startsWithRecursive(root, prefix, 0);
        }

        private boolean startsWithRecursive(TrieNode p, String prefix, int idx) {
            if (idx == prefix.length()) {
                return true;
            }

            char ch = prefix.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                return false;
            }
            return startsWithRecursive(node, prefix, idx + 1);
        }

        public void delete(String word) {
            delete(root, word, 0);
        }
        
        /**
         * Returns true if parent should delete the mapping
         */
        private boolean delete(TrieNode p, String word, int idx) {
            if (idx == word.length()) {
                if (!p.endOfWord) {
                    return false;
                }
                p.endOfWord = false;
                return p.children.size() == 0;
            }

            char ch = word.charAt(idx);
            TrieNode node = p.children.get(ch);
            if (node == null) {
                return false;
            }

            // continue tracking p's child node and also return information
            // on if or not to delete the calling node
            boolean shouldDeleteNode = delete(node, word, idx + 1);

            if (shouldDeleteNode) {
                p.children.remove(ch);
                return p.children.size() == 0;
            }

            return false;
        }

    }
}    for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, new StringBuilder(), trie, set);
            }
        }
        
        return new ArrayList<>(set);
    }
    
    private void dfs(char[][] board, int i, int j, StringBuilder sb, Trie trie, Set<String> set) {
        // pre-condition: i,j are valid and board[i][j] has not visited
        char c = board[i][j];
        sb.append(c);
        String next = sb.toString();
        if (!trie.startsWith(next)) return;
        if (trie.search(next)) {
            System.out.println(next);
            set.add(next);
            // can'r just return here because next may be prefix of other words
        }
        
        board[i][j] = '#';
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length || board[di][dj] == '#') continue;
            dfs(board, di, dj, sb, trie, set);
            sb.deleteCharAt(sb.length() - 1);
        }
        board[i][j] = c;
    }

    private class Trie {
        private TrieNode root;

        private class TrieNode {
            private TrieNode[] children;
            private String word;
            public TrieNode() {
                children = new TrieNode[26];
                word = ""; // without it, 124 occur null pointer error
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (p.children[idx] == null) 
                    p.children[idx] = new TrieNode();
                p = p.children[idx];
            }
            p.word = word;
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (p.children[idx] == null) return false;
                p = p.children[idx];
            }
            return p.word.equals(word);
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                int idx = prefix.charAt(i) - 'a';
                if (p.children[idx] == null) return false;
                p = p.children[idx];
            }
            return true;
        }
    }


    // Solution 1: use LC 79 Word Search: TLE
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) ans.add(word);
        }
        return ans;
    }
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] != word.charAt(idx)) return false;
        
        char cur = board[i][j];
        board[i][j] = '#'; // visited
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; k++) { // expand; once there is a successful path, return true;
            if (dfs(board, word, i + dirs[k], j + dirs[k + 1], idx + 1)) {
                board[i][j] = cur;
                return true;
            }
        }
        board[i][j] = cur; // restore and can be used for other paths
        return false; // no such path
    }

    // Solution 2: using TrieNode
    private static class TrieNode {
        private TrieNode[] children;
        private String word;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode root = new TrieNode();

        buildTrie(root, words);

        for (int i = 0; i < board.length; i++) 
            for (int j = 0; j < board[0].length; j++) 
                dfs(board, i, j, root, ans);
        return ans;
    }

    private void buildTrie(TrieNode root, String[] words) {
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (p.children[idx] == null)
                    p.children[idx] = new TrieNode();
                p = p.children[idx];
            }
            p.word = w;
        }
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> ans) {
        // i, j are valid and unvisited
        char c = board[i][j];
        if (p.children[c - 'a'] == null) return; // early stop
        
        p = p.children[c - 'a'];
        if (p.word != null) { // found one
            ans.add(p.word);
            p.word = null; // de-deplicate
        }
        
        board[i][j] = '#';
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= board.length || dj < 0 || dj >= board[0].length || board[di][dj] == '#') continue;
            dfs(board, di, dj, p, ans);
        }
        board[i][j] = c;
    }

    // Solution 3: with Trie class: check i,j at beginning
    private class Trie {
        private TrieNode root;

        private class TrieNode {
            private TrieNode[] children;
            private String word;
            public TrieNode() {
                children = new TrieNode[26];
                word = ""; // without it, 124 occur null pointer error
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (p.children[idx] == null) 
                    p.children[idx] = new TrieNode();
                p = p.children[idx];
            }
            p.word = word;
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (p.children[idx] == null) return false;
                p = p.children[idx];
            }
            return p.word.equals(word);
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                int idx = prefix.charAt(i) - 'a';
                if (p.children[idx] == null) return false;
                p = p.children[idx];
            }
            return true;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> ans = new HashSet<>();
        Trie trie = new Trie();
        for (String w : words) trie.insert(w);

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                dfs(board, i, j, "", visited, trie, ans);
        return new ArrayList<>(ans);
    }

    private void dfs(char[][] board, int i, int j, String str, boolean[][] visited, Trie trie, Set<String> ans) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (visited[i][j]) return;

        str += board[i][j];
        if (!trie.startsWith(str)) return;
        if (trie.search(str)) ans.add(str);

        visited[i][j] = true;
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; k++)
            dfs(board, i + dirs[k], j + dirs[k + 1], str, visited, trie, ans);
        visited[i][j] = false;
    }
}
