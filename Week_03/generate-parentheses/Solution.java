class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res,0,0,n,"");
        return res;
    }

    void generate(List<String> res,int left,int right,int max,String sb){
        if(right>=max){
            res.add(sb.toString());
            return;
        }
        if(left<max){
            generate(res,left+1,right,max,sb+"(");
        }
        if(right<left){
            generate(res,left,right+1,max,sb+")");
        }
    }


}