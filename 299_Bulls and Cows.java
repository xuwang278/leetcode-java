class Solution {
    public String getHint(String secret, String guess) {
        int[] counts = new int[10];
        int A = 0, B = 0;
        for (int i = 0; i < secret.length(); i++) {
        	if (secret.charAt(i) == guess.charAt(i)) A++;
        	else counts[secret.charAt(i) - '0']++; // mark secret
        }

        for (int i = 0; i < secret.length(); i++) {
        	if (secret.charAt(i) != guess.charAt(i) && counts[guess.charAt(i) - '0'] > 0) {
        		// hitten by secret
        		counts[guess.charAt(i) - '0']--;
        		B++;
        	} 
        		
        }
        
        return A + "A" + B + "B";
    }
}