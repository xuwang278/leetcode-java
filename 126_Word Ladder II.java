class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList); // set version of wordList for quick look-up
        List<List<String>> ans = new ArrayList<>(); 
        Map<String, List<String>> g = new HashMap<>(); // String -> list of strings one step away
        Map<String, Integer> map = new HashMap<>(); // String -> # of steps away from beginWord

        bfs(beginWord, endWord, dict, g, map);
        dfs(beginWord, endWord, g, map, new ArrayList<>(), ans);
        return ans;
    }

    private void bfs(String beginWord, String endWord, Set<String> dict, Map<String, List<String>> g, Map<String, Integer> map) {
        // create vertices for every words that will shown up during searching
        for (String word : dict) {
            g.put(word, new ArrayList<>());
        }
        g.put(beginWord, new ArrayList<>());

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        map.put(beginWord, 0);
        boolean found = false; // once find endWord, terminate while after finish all the other strings in current level
        while (!q.isEmpty() && !found) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                String top = q.poll();
                int step = map.get(top);
                List<String> neighbors = expand(top, dict);
                
                for (String next : neighbors) {
                    // update digraph: top -> next
                    g.get(top).add(next);
                    // next has been visited, the shortest distance to beginWord has been stored
                    // do not overwrite
                    if (map.containsKey(next)) continue; // lazy: seen before
                    map.put(next, step + 1);
                    if (next.equals(endWord)) found = true;
                    else q.offer(next);
                }
            }
        }
    }

    // no removals from dict
    private List<String> expand(String s, Set<String> dict) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char[] chs = s.toCharArray();
            char ch = s.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == ch) continue;
                chs[i] = c;
                String next = String.valueOf(chs);
                if (dict.contains(next)) list.add(next);
            }
        }
        return list;
    }

    private void dfs(String word, String endWord, Map<String, List<String>> g, Map<String, Integer> map, List<String> list, List<List<String>> ans) {
        if (word.equals(endWord)) {
            list.add(endWord);
            ans.add(new ArrayList<>(list));
            return;
        }

        list.add(word);
        for (String next : g.get(word)) {
            if (map.get(next) == map.get(word) + 1) {
                dfs(next, endWord, g, map, list, ans);
                list.remove(list.size() - 1);
            }
        }
        
    }

    // Sol 2:
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return ans;

        Map<String, Integer> steps = new HashMap<>(); // word -> steps to it
        //steps.put(beginWord, 1);
        Map<String, Set<String>> parents = new HashMap<>(); // String -> its parent (where it derives from)
        boolean found = false; // 如果找到解了, 把当前这一层做完, 即可跳出while
        
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int step = 0;
        while (!q.isEmpty() && !found) {
            step++;
            int size = q.size();
            for (int s = 0; s < size; s++) {
                String p = q.poll(); // parent
                for (int i = 0; i < endWord.length(); i++) {
                    char ch = p.charAt(i);
                    char[] chs = p.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ch) continue;
                        chs[i] = c;
                        String next = String.valueOf(chs);

                        // endWord
                        // next == endWord要单独处理下, 因为如果用后面代码处理, 遇到第一次endWord
                        // 就把它从dict里删除了, 其他路径到endWord的情况就无法涉及
                        if (next.equals(endWord)) {
                            parents.putIfAbsent(next, new HashSet<>()); // first hit, create set
                            parents.get(next).add(p);
                            found = true;
                            continue; 
                        } else {
                            // old word seen by previous word in the same level
                            // 如果该层前一个节点也可以变形为next, 那么当下这个p也会next一个合法的父节点
                            // 因为到二者到next的距离相同
                            // not a new word but another transform with 
                            // with the same number of steps
                            if (steps.containsKey(next) && step + 1 == steps.get(next)) {
                                parents.get(next).add(p); // p is another parent of next
                                // steps of next不变
                                continue;
                            }
                        }

                        // a strange word
                        if (!dict.contains(next)) continue;
                        
                        // a new word
                        dict.remove(next); // 移除它此后不再考虑next, 因为bfs已经找到到next的最短变化路径了
                        q.offer(next);
                        steps.put(next, step + 1);
                        parents.putIfAbsent(next, new HashSet<>()); // use put is OK as this is a new word
                        parents.get(next).add(p);
                    }
                }

            }
        }
                
        if (found) {
            List<String> list = new ArrayList<>();
            list.add(endWord);
            dfs(endWord, beginWord, parents, list, ans);
        }
        return ans;
    }

    private void dfs(String word, String beginWord, Map<String, Set<String>> parents, List<String> list, List<List<String>> ans) {
        if (word.equals(beginWord)) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (String p : parents.get(word)) {
            list.add(0, p);
            dfs(p, beginWord, parents, list, ans);
            list.remove(0); // 由于是往头加, 所以回溯时删除头
        }
    }
}

























