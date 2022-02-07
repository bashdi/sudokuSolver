package com.github.bashdi.sudokusolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SudokuFrame extends JFrame {

    final int GRID_SIZE = 9;
    int[][] sudoku = new int[GRID_SIZE][GRID_SIZE];
    HashMap<Point, JTextField> textFieldMap = new HashMap<>();

    public SudokuFrame() {
        setTitle("SudokuSolver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2,2,2);
        gbc.fill = GridBagConstraints.BOTH;

        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                gbc.gridx = c;
                gbc.gridy = r;
                JTextField jTextField = new JTextField();
                jTextField.setPreferredSize(new Dimension(30, 30));
                add(jTextField, gbc);
                textFieldMap.put(new Point(c, r), jTextField);
            }

        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        add(solveButton);


        pack();
        setVisible(true);
    }

    public void solve() {
        //get sudoku
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                String textfieldContent = textFieldMap.get(new Point(c, r)).getText();
                int textfieldInt = 0;
                if (!textfieldContent.isBlank()) {
                    textfieldInt = Integer.valueOf(textfieldContent);
                }

                sudoku[r][c] = textfieldInt;
            }
        }

        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        sudokuSolver.solve();
        sudoku = sudokuSolver.getSudoku();

        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                textFieldMap.get(new Point(c, r)).setText(String.valueOf(sudoku[r][c]));
            }
        }
    }

}
