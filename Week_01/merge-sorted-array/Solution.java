class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n ==0) return;
        if(m==0){
            for(int i=0;i<nums2.length;i++){
                nums1[i]=nums2[i];
            }
        }
        int i = 0;
        int j = 0;
        while(i<m && j<n){
            if(nums1[i+j]>=nums2[j]){
                for(int k=j+m;k>i+j;k--){
                    nums1[k]=nums1[k-1];
                }
                nums1[i+j]=nums2[j];
                j++;
            }else if(i==m-1){
                for(int k=j;k<n;k++){
                    nums1[i+k+1]=nums2[k];
                }
                j++;
            }else{
                i++;
            }
        }
    }
}