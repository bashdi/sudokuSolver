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
    JLabel triesLabel;

    public SudokuFrame() {
        setTitle("SudokuSolver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);



        //Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2,2,2);
        gbc.fill = GridBagConstraints.BOTH;



        //create sudoku grid with jtextfields
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                gbc.gridx = c;
                gbc.gridy = r;
                JTextField jTextField = new JTextField();
                jTextField.setPreferredSize(new Dimension(30, 30));
                jTextField.setHorizontalAlignment(JTextField.CENTER);
                add(jTextField, gbc);
                textFieldMap.put(new Point(c, r), jTextField);

                //color grid
                if (r < 3 || r >= 6) {
                    if (c < 3 || c >= 6) {
                        jTextField.setBackground(Color.orange);
                    } else {
                        jTextField.setBackground(Color.yellow);
                    }
                } else {
                    if (c < 3 || c >= 6) {
                        jTextField.setBackground(Color.yellow);
                    } else {
                        jTextField.setBackground(Color.orange);
                    }
                }
            }

        }



        //solveButton
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSodoku();
            }
        });
        gbc.gridx = 9;
        gbc.gridy = 0;
        add(solveButton, gbc);



        //clearButton
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGrid();
            }
        });
        gbc.gridx = 9;
        gbc.gridy = 1;
        add(clearButton, gbc);



        //triesLabel
        triesLabel = new JLabel();
        gbc.gridx = 9;
        gbc.gridy = 2;
        add(triesLabel, gbc);



        pack();
        setVisible(true);
    }



    public void solveSodoku() {
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

        triesLabel.setText("Tries: " + sudokuSolver.getSolveCalls());
    }


    private void clearGrid() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                textFieldMap.get(new Point(c, r)).setText("");
            }
        }
        triesLabel.setText("");
    }

}
