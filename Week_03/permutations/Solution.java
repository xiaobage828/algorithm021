class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        generatePermute(res,list,nums);
        return res;
    }

    public void generatePermute(List<List<Integer>> res,List<Integer> list,int[] nums){
        if(list.size()>=nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int f:nums){
            if(!list.contains(f)){
                list.add(f);
                generatePermute(res,list,nums);
                list.remove(list.size()-1);
            }
        }
    }

}