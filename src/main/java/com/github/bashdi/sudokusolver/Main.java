package com.github.bashdi.sudokusolver;

public class Main {

    public static void main(String[] args) {
        int[][] sudoku = {
            {5, 0, 3, 0, 0, 4, 6, 7, 0},
            {0, 9, 0, 2, 5, 0, 8, 3, 1},
            {0, 0, 2, 6, 0, 3, 0, 0, 9},
            {0, 2, 0, 3, 7, 0, 0, 1, 5},
            {0, 0, 8, 0, 2, 0, 7, 6, 0},
            {3, 0, 0, 5, 6, 0, 0, 0, 0},
            {4, 6, 0, 0, 0, 0, 1, 0, 7},
            {2, 8, 1, 0, 4, 0, 0, 0, 0},
            {0, 0, 5, 0, 9, 0, 0, 8, 0}
        };

        SudokuSolver ss = new SudokuSolver(sudoku);

        boolean isSolved = ss.solve();
        System.out.println(isSolved);

        if (isSolved) {
            for (int i = 0; i < 9; i++) {
                int[] row = sudoku[i];
                for (int j = 0; j < 9; j++) {
                    System.out.print(row[j]);
                }
                System.out.println();
            }
        }

    }
}
