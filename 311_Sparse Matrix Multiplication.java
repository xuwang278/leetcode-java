class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0 ||
            (A[0].length != B.length)) {
            return new int[][] {};
        }


        int[][] ans = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) { 
                for (int k = 0; k < A[0].length; k++) {// m1 should equal to n2
                    ans[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return ans;
    }

    // Sol 2 using map
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0 ||
            (A[0].length != B.length)) {
            return new int[][] {};
        }

        Map<Integer, int[]> rowInA = new HashMap<>();
        Map<Integer, int[]> colInB = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    rowInA.put(i, A[i]);
                    break;
                }
            }
        }

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                if (B[i][j] != 0) {
                    int[] temp = new int[B.length];
                    for (int k = 0; k < B.length; k++) {
                        temp[k] = B[k][j];
                    }
                    colInB.put(j, temp);
                }
            }
        }
        
        int[][] ans = new int[A.length][B[0].length];
        for (int i : rowInA.keySet()) {
            for (int j : colInB.keySet()) {
                for (int k = 0; k < A[0].length; k++) {
                    ans[i][j] += rowInA.get(i)[k] * colInB.get(j)[k];
                }
            }
        }
        return ans;
    }
    
}