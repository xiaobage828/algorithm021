class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //关键在于实现与key相关的最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(k,new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(b) - map.get(a);
            }
        });
        heap.addAll(map.keySet());
        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i]=heap.poll();
        }
        return res;
    }
}