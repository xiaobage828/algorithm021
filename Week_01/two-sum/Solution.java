class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] tmp = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            tmp[i]=target-nums[i];
            for(int j=0;j<i;j++){
                if(tmp[j]==nums[i]){
                    return new int[]{j,i};
                }
            }
        }
        return null;
    }
}