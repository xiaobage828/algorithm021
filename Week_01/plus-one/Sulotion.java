class Solution {
    public int[] plusOne(int[] digits) {
        int length = digits.length-1;
        for(int i=length ; i>=0 ; i--){
            if(digits[i]!=9){
                digits[i]++;
                return digits;
            }else{
                digits[i]=0;
            }
        }
        length=length+2;
        int[] res = new int[length];
        res[0]=1;
        for(int i=1;i<length;i++){
            res[i]=0;
        }
        return res;
    }
}