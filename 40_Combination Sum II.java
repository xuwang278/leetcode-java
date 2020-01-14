class Solution {
    // T: O(2^n) -> O(n! * n) 
    // S: O(n)
    // Back Tracking
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        
        Arrays.sort(candidates);

        List<Integer> curr = new ArrayList<>();
        dfs(candidates, target, 0, curr, res);
        return res;
    }
    
    private void dfs(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) return; // no need to iterate
            if (i > start && candidates[i] == candidates[i - 1]) continue; // avoid duplicates
            
            curr.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, curr, res); // avoid select twice
            curr.remove(curr.size() - 1);
        }
    }

    // using set to avoid duplicate
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>(), set);
        
        List<List<Integer>> ans = new ArrayList<>();
        ans.addAll(set);
        return ans;
    }
    
    private void dfs(int[] candidates, int start, int remining, List<Integer> list, Set<List<Integer>> set) {
        if (remining == 0) {
            set.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remining) return;
            //if (i > start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            dfs(candidates, i + 1, remining - candidates[i], list, set);
            list.remove(list.size() - 1);
        }
    }
}