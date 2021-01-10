class Solution {
    public boolean canCross(int[] A) {
        int n = A.length;

        //每一块石头作为下标
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
        int i, j;

        //初始化
        for (i = 0; i < n; i++) {
            dp.put(A[i], new HashSet<>());
        }
        //一开始就在初始位置
        dp.get(A[0]).add(0);

        //看每一块石头
        for (i = 0; i < n; i++) {
            //获取这块石头对应的Si
            HashSet<Integer> Si = dp.get(A[i]);
            //k = previous jump
            for (Integer k : Si) {
                //j = current jump
                for (j = k - 1; j <= k + 1; j++) {
                    //只能往右跳
                    if (j <= 0) {
                        continue;
                    }
                    //如果有这块石头，需要更新
                    if (dp.containsKey(A[i] + j)) {
                        dp.get(A[i] + j).add(j);
                    }
                }
            }
        }
        return !dp.get(A[n - 1]).isEmpty();
    }
}
