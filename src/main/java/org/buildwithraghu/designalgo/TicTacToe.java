package org.buildwithraghu.designalgo;

class TicTacToe {

    private final int n;
    private final int[][] hor, ver;
    private final int[] diag, adiag;

    public TicTacToe(int n) {
        this.n = n;
        this.hor = new int[n][3];
        this.ver = new int[n][3];
        this.diag = new int[3];
        this.adiag = new int[3];
    }

    public int move(int row, int col, int player) {
        hor[row][player]++;
        ver[col][player]++;
        if (row == col)
            diag[player]++;
        if (row + col == n - 1)
            adiag[player]++;
        return won(row, col, player) ? player : 0;
    }

    private boolean won(int row, int col, int player) {
        return diag[player] == n ||
                adiag[player] == n ||
                hor[row][player] == n ||
                ver[col][player] == n;
    }
}