class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 0;
        int index = 0;
        while(index<nums.length){
            if(index>0&&nums[index]!=nums[index-1]){
                count++;
                nums[count]=nums[index];
            }
            index++;
        }
        return count+1;
    }
}