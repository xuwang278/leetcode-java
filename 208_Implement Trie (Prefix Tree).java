class Trie {
    // https://www.youtube.com/watch?v=f48wGD-MuQw
    private class TrieNode {
        private TrieNode[] children;
        private String word;
        private boolean end;
        public TrieNode() {
            children = new TrieNode[26];
            word = ""; // without it, 124 occur null pointer error
            end = false; // optional
        }
    }

    private TrieNode root;

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
        p.end = true;
    }

    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) return false;
            p = p.children[idx];
        }
        return p.word.equals(word);
        // return p.end
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

// Recursive + delete 
// https://www.youtube.com/watch?v=AXjmTQ8LEoI
// https://github.com/mission-peace/interview/blob/master/src/com/interview/suffixprefix/Trie.java
class Trie {
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
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(word, root, 0);
    }

    private void insert(String word, TrieNode root, int i) {
        if (i == word.length()) {
            root.end = true;
            root.word = word; 
            return;
        }

        int index = (int) (word.charAt(i) - 'a');
        if (root.children[index] == null)
            root.children[index] = new TrieNode();
        insert(word, root.children[index], i + 1);
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    private boolean search(String word, TrieNode root, int i) {
        if (i == word.length()) return root.end; // return root.word.equals(word);
        int index = (int) (word.charAt(i) - 'a');
        if (root.children[index] == null) return false;
        return search(word, root.children[index], i + 1);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, root, 0);
    }

    private boolean startsWith(String prefix, TrieNode root, int i) {
        if (i == prefix.length()) return true;
        int index = (int) (prefix.charAt(i) - 'a');
        if (root.children[index] == null) return false;
        return startsWith(prefix, root.children[index], i + 1);
    }

    /** Delete word from trie */
    public void delete(String word) {
        delete(word, root, 0);
    }

    // return true if parent should delete the children
    private boolean delete(String word, TrieNode root, int i) {
        if (i == word.length()) {
            // word does not exist (not inserted as a word)
            if (!root.end) return false;

            root.end = false;
            return hasNoChildren(root.children);
        }
        
        int index = (int) (word.charAt(i) - 'a');
        if (root.children[index] == null) return false; // word does not exist

        boolean deleteChild = delete(word, root.children[index], i + 1);
        if (deleteChild) {
            root.children[index] = null;
            return hasNoChildren(root.children); // any other children?
        }

        return false;
    }

    private boolean hasNoChildren(TrieNode[] children) {
        for (TrieNode child : children) {
            if (child != null) return false;
        }
        return true;
    }

}