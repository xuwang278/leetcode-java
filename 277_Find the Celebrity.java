/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
    	// knows(a, b) = true -> a can't be celebrity
    	// konws(a, b) = false -> a could be celebrity
        int candidate = 0;
        for (int i = 1; i < n; i++){
            if (knows(candidate, i)) 
                candidate = i; // i 可能是明星; candudate一定不是明星了, 因为他认识i
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate)
                // 如果candidate认识别人 或者 别人不认识他, 那他不是名人
                if (knows(candidate, i) || !knows(i, candidate))
                    return -1;
        }
        return candidate;
    }
    
}