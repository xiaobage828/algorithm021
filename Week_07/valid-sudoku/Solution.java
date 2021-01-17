class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length > 9 || board[0].length > 9 ) {
            return false;
        }

        return backTrace(board, 0, 0);
    }

    public boolean backTrace(char[][] board, int row, int col) {
        //如果col越界，那么重新指向下一行
        if (col == 9) {
            return backTrace(board,row+1,0);
        }
        //如果row越界，说明遍历完成，那么当前返回true
        if (row == 9) {
            return true;
        }

        //如果当前位置没有显示数字，直接跳向下一个位置
        if (board[row][col] == '.') {
            return backTrace(board,row,col+1);
        }
        // 这个位置数字已给出，需要试探，
        if (board[row][col] != '.') {
            //如果在当前位置上不成立直接返回false
            if (!isValid(board, row, col)) {
                return false;
            }
            //如果这个位置上成立。直接试探下一个位置
            return backTrace(board,row,col+1);
        }

        //如果全部遍历之后，没有false那么就返回true
        return true;
    }

    private boolean isValid(char[][] board, int row, int col) {
        char ch = board[row][col];
        // 三个方向，任一方向，其它8个位置上的数和当前位置ch不能相同
        for (int k = 0; k < 9; k++) {
            // 同一行九个位置已出现 ch
            if (board[row][k] == ch) {
                if (k != col) {
                    return false;
                }
            }
            // 同一列九个位置中已出现 ch
            if (board[k][col] == ch){
                if (k != row) {
                    return false;
                }
            }

            //看其他8个位置是否出现ch
            // 同一个子数独九个位置中已出现 ch
            if (board[(row / 3) * 3 + k / 3][(col / 3) * 3 + k % 3] == ch){
                if ((row / 3) * 3 + k / 3 != row && (col / 3) * 3 + k % 3 != col) {
                    return false;
                }
            }
        }
        return true;
    }
}