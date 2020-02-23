class Solution {
	// Sol 1: Brute Force + Pruning
	// T: O(sum(wi^2)) wi - leanth of word[i]
	// S: O(n + sum(wi)) n - # of words
	// Fastest solution though it's a n^2 algo
	public String longestWord(String[] words) {
		Set<String> set = new HashSet<>();
		for (String w : words) set.add(w); // add words in set

		String ans = "";
		for (String w : words) {
			// pruning
			if (w.length() < ans.length() || 
				w.length() == ans.length() && ans.compareTo(w) < 0) continue;

			// check if every of w's prefix is in set
			boolean valid = true;
			for (int i = 1; i < w.length() && valid; i++) {
				String prefix = w.substring(0, i);
				if (!set.contains(prefix)) valid = false;
			}
			if (valid) ans = w;
		}
		return ans;
	}

	// Sol 2: Brute Force + Sort
	// T: O(sum(wi^2) + nlogn) wi - leanth of word[i]
	// S: O(n + sum(wi)) n - # of words
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
		for (String w : words) set.add(w); // add words in set

		// sort by length desc, if tie, sort by lexcial order
		Arrays.sort(words, (a, b) -> a.length() == b.length() ? 
        	a.compareTo(b) : b.length() - a.length());

		// Arrays.sort(words, new Comparator<String>() {
  //           @Override
  //           public int compare(String a, String b) {
  //               if (a.length() == b.length()) return a.compareTo(b);
  //               return b.length() - a.length();
  //           }
  //       });

		for (String w : words) {
			boolean valid = true;
			for (int i = 1; i < w.length() && valid; i++) {
				String prefix = w.substring(0, i);
				if (!set.contains(prefix)) valid = false;
			}
			if (valid) return w;
		}
		return "";
    }

    // Sol 3: Trie + Pruning
    // T: O(sum(wi)) wi - leanth of word[i]
	// S: O(26 * w * n) n - # of words
    public String longestWord(String[] words) {
    	Trie trie = new Trie();
    	for (String w : words) trie.insert(w);

    	String ans = "";
    	for (String w : words) {
    		// pruning
			if (w.length() < ans.length() || 
				w.length() == ans.length() && ans.compareTo(w) < 0) continue;

			if(trie.hasAllPrefixes(w)) ans = w;
    	}
    	return ans;
    }

    // Sol 4: Trie + Sorting
    // T: O(sum(wi) + nlogn) wi - leanth of word[i]
	// S: O(26 * w * n) n - # of words
    public String longestWord(String[] words) {
    	Trie trie = new Trie();
    	for (String w : words) trie.insert(w);

    	// sort by length desc, if tie, sort by lexcial order
		Arrays.sort(words, (a, b) -> a.length() == b.length() ? 
        	a.compareTo(b) : b.length() - a.length());

		for (String w : words) {
			if (trie.hasAllPrefixes(w)) return w;
		}
    	return "";
    }
    
    // Trie class - LC 208
    private class Trie {
    	private TrieNode root;

    	private class TrieNode {
	    	private TrieNode[] children;
	    	private boolean end;
	    	private String word;

	    	public TrieNode() {
	    		children = new TrieNode[26];
	    		end = false;
	    		word = "";
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
    		p.end = true;
    		p.word = word;
    	}

    	public boolean hasAllPrefixes(String word) {
    		TrieNode p = root;
    		for (int i = 0; i < word.length(); i++) {
    			int idx = word.charAt(i) - 'a';
    			if (p.children[idx] == null) return false; 
    			p = p.children[idx];
    			if (!p.end) return false; // prefix not a complete word
    		}
    		return true;
    	}
    }

}