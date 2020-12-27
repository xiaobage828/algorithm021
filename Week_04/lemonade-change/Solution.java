//模拟加贪心
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for(int i=0;i<bills.length;i++){
            if(bills[i]==5){
                five++;
            }else if(bills[i]==10&&five>=1){
                ten++;
                five--;
            }else if(bills[i]==20&&ten>=1&&five>=1){
                ten--;
                five--;
            }else if(bills[i]==20&&five>=3){
                five-=3;
            }else{
                return false;
            }
        }
        return true;
    }
}