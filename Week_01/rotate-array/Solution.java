class Solution {
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        int count = 0;
        for(int i=0;count<nums.length;i++){
            int cur = i;
            int pre =  nums[cur];
            do{
                int next = (cur+k)%nums.length;
                int tmp = nums[next];
                nums[next] = pre;
                pre = tmp;
                cur = next;
                count++;
            }while(cur!=i);
        }
    }
}