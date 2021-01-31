class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        if(jewels==null||jewels.length()==0||stones==null||stones.length()==0) return 0;
        int[] chs = new int[52];
        for(char c:jewels.toCharArray()){
            if('A'<=c&&'Z'>=c){
                chs[c-'A']=1;
            }else if('a'<=c&&'z'>=c){
                chs[c-'a'+26]=1;
            }
        }
        int count = 0;
        for(char c:stones.toCharArray()){
            if( ('A'<=c&&'Z'>=c&&chs[c-'A']==1) || ('a'<=c&&'z'>=c && chs[c-'a'+26]==1) ){
                count++;
            }
        }
        return count;
    }
}