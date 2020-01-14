class Solution {
    // Time: O(2^n)
    // Space: O(n)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;

        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
 
    private void dfs(int[] candidates, int target, int start, List<Integer> list, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list)); 
            return;
        }
        
        for (int i = start; i < candidates.length; i++) { // combination: always look forward
            int selected = candidates[i];
            list.add(selected);
            dfs(candidates, target - selected, i, list, res);
            list.remove(list.size() - 1);
        }
    }

    // https://www.youtube.com/watch?v=4sROSJN_I6E&t=445s 4:20'
	// T: O(2^n) -> O(n! * n) with break ? 
	// S: O(n)
	// Back Tracking
    // see P46 for permutation/combination templates
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;

        Arrays.sort(candidates);

        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // permutation: i starts from 0
        // combination: i starts from start, so that
        // e.g., when (2,3) is found, then (3,2) should be ignored due to same combination
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // no need to continue looping
            curr.add(candidates[i]);
            // use i + 1 instead to avoid duplicates in combination if no duplicatios in candidates;
            // if duplicates in candidates, same repeated number may be chosen
            dfs(candidates, target - candidates[i], i, curr, res); 
            curr.remove(curr.size() - 1);
        }
    }

}