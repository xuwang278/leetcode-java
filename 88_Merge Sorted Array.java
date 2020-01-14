class Solution {
	// T: O(m+n)
	// S: O(m)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
       // int[] aux = Arrays.copyOf(nums1, m); // Arrays.copyOf(nums1, 0, m)
    	int[] aux = new int[m];
    	for (int i = 0; i < m; i++)
    		aux[i] = nums1[i];

        int i = 0, j = 0;
        for (int k = 0; k < m + n; k++){
        	if (i >= m) nums1[k] = nums2[j++];
        	else if (j >= n) nums1[k] = aux[i++];
        	else if (aux[i] < nums2[j]) nums1[k] = aux[i++];
        	else nums1[k] = nums2[j++];
        }
        
    }
}