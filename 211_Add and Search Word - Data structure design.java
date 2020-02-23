class WordDictionary {
    // latest version
    private class TrieNode {
        private TrieNode[] children;
        private String word;
        private boolean end;
        
        public TrieNode() {
            children = new TrieNode[26];
            word = "";
            end = false;
        }
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        add(word, root, 0);
    }
    
    private void add(String word, TrieNode root, int i) {
        if (i == word.length()) {
            root.word = word;
            root.end = true;
            return;
        }
        
        int idx = word.charAt(i) - 'a';
        if (root.children[idx] == null)
            root.children[idx] = new TrieNode();
        add(word, root.children[idx], i + 1);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root, 0);
    }
    
    private boolean search(String word, TrieNode root, int i) {
        if (i == word.length()) return root.end;
        char c = word.charAt(i);
        if (c != '.') {
            int idx = word.charAt(i) - 'a';
            if (root.children[idx] == null) return false;
            return search(word, root.children[idx], i + 1);
        } else { // 如果用else, return可以放在else里面; 如果用 else if, return 必须放在else外面, 否则整个函数缺少return值
            for (TrieNode node : root.children) {
                if (node != null && search(word, node, i + 1)) return true;
            } 
            return false; 
        }
    }
    
    // previous version
    private TrieNode root;

    private class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean end;

        public TrieNode() {
            children = new HashMap<>();
            end = false;
        }
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addWord(word, root, 0);
    }

    private void addWord(String word, TrieNode cur, int index) {
        if (index == word.length()) {
            cur.end = true;
            return;
        }

        char ch = word.charAt(index);
        TrieNode node = cur.children.get(ch);
        if (node == null) {
            node = new TrieNode();
            cur.children.put(ch, node);
        }

        addWord(word, node, index + 1);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    private boolean search(String word, TrieNode cur, int index) {
        if (index == word.length()) {
            return cur.end;
        }

        char ch = word.charAt(index);
        if (ch != '.') {
            TrieNode node = cur.children.get(ch);
            if (node == null) return false;
            return search(word, node, index + 1);
        } else {
            for (TrieNode node : cur.children.values()) {
                if(search(word, node, index + 1)) return true;
            }
        }
        return false;
    }
}