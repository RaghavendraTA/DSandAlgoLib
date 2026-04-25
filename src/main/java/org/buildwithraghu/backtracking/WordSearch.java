package org.buildwithraghu.backtracking;

public class WordSearch {

    // https://leetcode.com/problems/word-search
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (backtrack(board, word, m, n, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean backtrack(char[][] board, String word, int m, int n, int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(k)) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '\0';

        if (backtrack(board, word, m, n, i + 1, j, k + 1) ||
            backtrack(board, word, m, n, i - 1, j, k + 1) ||
            backtrack(board, word, m, n, i, j + 1, k + 1) ||
            backtrack(board, word, m, n, i, j - 1, k + 1))
        {
            return true;
        }

        board[i][j] = temp;
        return false;
    }
}
