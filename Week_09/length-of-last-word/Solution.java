class Solution {
    public int lengthOfLastWord(String s) {
        if(null == s || s.length() ==0) return 0;
        int left = -1;
        int right = s.length();
        int i = right - 1;
        while(i>=0&&s.charAt(i)==' '){
            right--;
            i--;
        }
        do{
            i--;
            if(i>=0&&s.charAt(i)==' '){
                left = i;
                break;
            }
        }while(i>=0);
        return right-left-1;
    }
}