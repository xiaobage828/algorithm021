class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i< arr2.length ; i++){
            map.put(arr2[i],i);
        }
        List<Integer> list = new ArrayList<>();
        for(int num1 : arr1){
            list.add(num1);
        }
        Collections.sort(list,new Comparator<Integer>(){
            @Override
            public int compare(Integer i1,Integer i2){
                int o1 = map.getOrDefault(i1,Integer.MAX_VALUE);
                int o2 = map.getOrDefault(i2,Integer.MAX_VALUE);
                if(o1==Integer.MAX_VALUE && o2==Integer.MAX_VALUE){
                    return i1 - i2 >= 0? 1:-1;
                }else{
                    return o1 - o2 >= 0? 1:-1;
                }
            }
        });
        int[] res = new int[arr1.length];
        for(int i = 0; i<list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}