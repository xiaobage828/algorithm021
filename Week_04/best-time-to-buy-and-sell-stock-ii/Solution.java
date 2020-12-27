class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null) return 0;
        int res = 0;
        for(int i=0;i<prices.length-1;i++){
            if(prices[i+1]>prices[i]){
                res+=prices[i+1]-prices[i];
            }
        }
        return res;
    }
}