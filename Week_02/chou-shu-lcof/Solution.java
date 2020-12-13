class Solution {
    public int nthUglyNumber(int n) {
        int[] factors = new int[]{2,3,5};
        HashSet<Long> seen = new HashSet();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();//最小堆
        heap.offer(1L);
        for(int i=1 ; i<n ; i++){
            long poll = heap.poll();
            for(int f:factors){
                long m = poll*f;
                if(!seen.contains(m)){
                    heap.offer(m);
                    seen.add(m);
                }
            }
        }
        return heap.poll().intValue();
    }
}