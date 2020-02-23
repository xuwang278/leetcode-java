class Solution {
    public int distributeCandies(int[] candies) {
        Arrays.sort(candies);
        int count = 1;
        for (int i = 1; i < candies.length && count < candies.length / 2; i++)
            if (candies[i] > candies[i - 1])
                count++;
        return count;
    }

    // Solution 2
    public int distributeCandies(int[] candies) {
        HashSet <Integer> set = new HashSet<> ();
        for (int c : candies) {
            set.add(c);
        }
        // set.size() = # of types of candies
        return Math.min(set.size(), candies.length / 2);
    }
    
}