class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>(k);
        generateCombine(res,list,k,n);
        return res;
    }

    void generateCombine(List<List<Integer>> res,List<Integer> list,int k,int n){
        if(list.size()==k){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=1;i<=n;i++){
            if(!list.contains(i)){
                if(!list.isEmpty()&&(list.get(list.size()-1)>i)){
                    continue;
                }
                list.add(i);
                generateCombine(res,list,k,n);
                list.remove(list.size()-1);
            }
        }
    }
}