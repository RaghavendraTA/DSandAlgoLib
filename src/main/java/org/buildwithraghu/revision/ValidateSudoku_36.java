package org.buildwithraghu.revision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidateSudoku_36 {

    public boolean isValidSudoku(char[][] board) {
        List<HashSet<Character>> horizontal = new ArrayList<>();
        List<HashSet<Character>> vertical = new ArrayList<>();
        List<HashSet<Character>> shortSet = new ArrayList<>();

        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            horizontal.add(new HashSet<>());
            vertical.add(new HashSet<>());
            shortSet.add(new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.')
                    continue;
                if (!horizontal.get(i).add(board[i][j]))
                    return false;
                if (!vertical.get(j).add(board[i][j]))
                    return false;
                int segment = ((i / 3) * 3 + (j / 3));
                if (!shortSet.get(segment).add(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}