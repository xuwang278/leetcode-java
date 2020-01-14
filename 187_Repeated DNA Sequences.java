class Solution {
    // T: O(n)
    // S: O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>(); // re avoid dup results
        Set<String> seen = new HashSet<>();

        int lo = 0, hi = 10;
        while (hi <= s.length()) {
            String sub = s.substring(lo, hi);
            if (seen.contains(sub)) {
                res.add(sub);
            } else {
                seen.add(sub);
            }
            lo++;
            hi++;
        }
        return new ArrayList(ans);
    }

}