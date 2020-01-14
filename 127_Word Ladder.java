class Solution {
    // T: O(n*26^l): 每一位的26种变化都要查看，一共有l位，最多路径上遇到全部n个词
    // S: O(n): q can as long as n，注意q装的是String
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int step = 0; // steps away from beg, e.g. beg -> top (step = 1)
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) { 
                String top = q.poll();
                for (int i = 0; i < top.length(); i++) { // for each 'digit'
                    char[] chars = top.toCharArray();   
                    // char ch = chars[i]; // not necessary
                    for (char c = 'a'; c <= 'z'; c++) { // expand by modifying the digit to other 25 letters
                        //if (ch == c) continue; 
                        chars[i] = c;
                        String next = new String(chars);
                        if (next.equals(endWord)) return step + 2; // step + 1 include end, + 1 again to include beg
                        if (!words.contains(next)) continue;
                        words.remove(next); // each word can only be used once
                        q.offer(next);
                    }
                }
            }
            step++;
        }
        return 0;
    }

}



