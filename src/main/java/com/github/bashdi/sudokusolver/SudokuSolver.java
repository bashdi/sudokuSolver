package com.github.bashdi.sudokusolver;

public class SudokuSolver {

    int[][] sudoku;
    final static int GRID_SIZE = 9;

    public SudokuSolver(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public boolean solve() {
        return solve(this.sudoku);
    }

    private boolean solve(int[][] sudoku) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                int number = sudoku[r][c];
                if (number != 0) continue;

                for (int i = 1; i <= 9; i++) {
                    if (isNumberValid(i, r, c)) {
                        sudoku[r][c] = i;
                        if (solve(sudoku)) {
                            return true;
                        } else {
                            sudoku[r][c] = 0;
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isNumberInRow(int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (sudoku[row][i] == number) return true;
        }
        return false;
    }

    private boolean isNumberInColumn(int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (sudoku[i][column] == number) return true;
        }
        return false;
    }

    private boolean isNumberInBlock(int number, int row, int column) {
        int blockRow = row - (row % 3);
        int blockColumn = column - (column % 3);

        for (int r = blockRow; r < blockRow + 3; r++) {
            for (int c = blockColumn; c < blockColumn + 3; c++) {
                if (sudoku[r][c] == number) return true;
            }
        }
        return false;
    }

    private boolean isNumberValid(int number, int row, int column) {
        return !isNumberInRow(number, row)
                && !isNumberInColumn(number, column)
                && !isNumberInBlock(number, row, column);
    }
}
