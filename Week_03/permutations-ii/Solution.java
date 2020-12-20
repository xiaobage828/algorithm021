class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        generatePermuteUnique(list,res,nums,new int[nums.length]);
        return res;
    }

    void generatePermuteUnique(List<Integer> list, List<List<Integer>> res,int[] nums,int[] visited){
        if(list.size()==nums.length){
            List<Integer> listCopy = new ArrayList<>(list);
            if(!res.contains(listCopy)){
                res.add(listCopy);
            }
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(visited[i]==0){
                list.add(nums[i]);
                visited[i]=1;
                generatePermuteUnique(list,res,nums,visited);
                list.remove(list.size()-1);
                visited[i]=0;
            }
        }
    }
}