class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int temp = 0;
        for(int i=0;i<n;i++){
            temp+=grid[0][i];
            dp[0][i]=temp;
        }
        temp = 0;
        for(int i=0;i<m;i++){
            temp+=grid[i][0];
            dp[i][0]=temp;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }
}