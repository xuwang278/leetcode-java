class Solution {
    // To find a (unique) path that meets some requirements, dfs should return boolean in which
    // when the path is found, terminate recursion imeadiately by returning true; 
    // if dfs's return type is void, the on-going list will eventually be empty in the process of back-tracking 

    // However, properly use void can also solve the problem with 3 key points:
    // (1) use global variable, i.e. List<Integer> ans
    // (2) when such a path is found in the process of dfs, asign ans to it: i.e.
    // if (start == s.length()) {
    //       if (list.size() > 2) 
    //           ans = new ArrayList<>(list);
    //       return;
    //  }
    // (3) ans can't initialize inside main function and pass to dfs,
    // as we only assign copy of ans to the valid path while leaving ans still points to empty
    
    // Sol 1: booldan dfs
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        dfs(S, 0, ans);
        return ans;
    }
    
    private boolean dfs(String s, int start, List<Integer> ans) {
        if (start == s.length() && ans.size() >= 3)
            return true;
        
        for (int i = 1; start + i <= s.length(); i++) {
            if (i > 1 && s.charAt(start) == '0') return false; // can't lead to the successful path
            long n = Long.valueOf(s.substring(start, start + i));
            if (n > Integer.MAX_VALUE) break;
            
            // compare with previous 2
            int size = ans.size();
            // if (size >= 2 && n < ans.get(size - 1) + ans.get(size - 2)) 
            //     continue; // n is small, try increment i to make a larger n
            // n will be even larger for next i, so just break instead of continue
            if (size >= 2 && n > ans.get(size - 1) + ans.get(size - 2)) return false;; 
            if (size <= 1 || n == ans.get(size - 1) + ans.get(size - 2)) {
                ans.add((int) n);
                if (dfs(s, start + i, ans)) return true; 
                ans.remove(ans.size() - 1);
            }
            
        }
        return false;
    }

    // Sol 2: void dfs
    private List<Integer> ans = new ArrayList<>();
    
    public List<Integer> splitIntoFibonacci(String S) {
        dfs(S, 0, new ArrayList<>());
        return ans;
    }
    
    private void dfs(String s, int start, List<Integer> list) {
        if (start == s.length()) {
            if (list.size() > 2)
                ans = new ArrayList<>(list);
            return;
        }
        
        for (int i = 1; start + i <= s.length(); i++) {
            if (i > 1 && s.charAt(start) == '0') return;
            long n = Long.valueOf(s.substring(start, start + i));
            if (n > Integer.MAX_VALUE) return;
            
            int size = list.size();
            if (size >= 2 && n > list.get(size - 1) + list.get(size - 2)) return;
            if (size <= 1 || n == list.get(size - 1) + list.get(size - 2)) {
                list.add((int) n);
                dfs(s, start + i, list);
                list.remove(list.size() - 1);
            }
        }
    }

    // Wrong: ans will eventually in the process of back-tracking 
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        dfs(S, 0, ans);
        return ans;
    }
    
    private void dfs(String s, int start, List<Integer> ans) {
        if (start == s.length()) return;
        
        for (int i = 1; start + i <= s.length(); i++) {
            String selected = s.substring(start, start + i);
            int cur = Integer.valueOf(selected);
            if (ans.size() > 1) {
                int last = ans.get(ans.size() - 1);
                int prev = ans.get(ans.size() - 2);
                if (cur != last + prev) continue;
            }
            ans.add(cur);
            dfs(s, start + i, ans);
            ans.remove(ans.size() - 1);
        }
    }

    // wrong: as we only assign copy of ans to the valid path while leaving ans still points to empty
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        dfs(S, 0, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(String s, int start, List<Integer> list, List<Integer> ans) {
        if (start == s.length()) {
            if (list.size() > 2) 
                ans = new ArrayList<>(list);
            return;
        }
        
        for (int i = 1; start + i <= s.length(); i++) {
            if (i > 1 && s.charAt(start) == '0') return;
            long n = Long.valueOf(s.substring(start, start + i));
            if (n > Integer.MAX_VALUE) return;
            
            int size = list.size();
            if (size >= 2 && n > list.get(size - 1) + list.get(size - 2)) return;
            if (size <= 1 || n == list.get(size - 1) + list.get(size - 2)) {
                list.add((int) n);
                dfs(s, start + i, list, ans);
                list.remove(list.size() - 1);
            }
        }
    }
    
}