class Solution {
    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) return "";
        String[] strs = S.split(" "); 
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            if (startsWithVowel(s)) {
                sb.append(s).append("ma");
            } else {
                sb.append(s.substring(1)).append(s.charAt(0)).append("ma");
            }
            
            postFix(sb, i + 1);
        }
        
        return sb.toString().trim();
    }
    
    private boolean startsWithVowel(String s) {
        char c = Character.toLowerCase(s.charAt(0));
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    
    private void postFix(StringBuilder sb, int n) {
        for (int i = 0; i < n; i++)
            sb.append("a");
        sb.append(" ");
    }
    
    // I speak Goat Latin, sb = 
    // i = 0; sb = Imaa" "
    // i = 1: sb = Imma" "peaksmaaa" "
    // i = 2: sb = Imma" "peaksmaaa" "oatGmaaaa" "
    // i = 3: sb = Imma" "peaksmaaa" "oatGmaaaa" "atinLmaaaaa
}