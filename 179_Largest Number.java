class Solution {

    private class SortByCombine implements Comparator<String> {
        public int compare(String a, String b) {
            return (b + a).compareTo(a + b);
        }
    }

    public String largestNumber(int[] nums) {
        String[] numStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStr, new SortByCombine());

        if (numStr[0].charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();
        for (String str : numStr) {
            sb.append(str);
        }
        return sb.toString();
    }
}

// 两个数串联起来，以哪个数开头大，哪个数大
a = "1211"
b = "12"

a + b = "121112" 
    <
b + a = "121211"

b > a