package sudoku;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        SudokuStructure example = new SudokuStructure(true);


        example.printSudoku();
        System.out.println(example.getRowIndex(72));
        System.out.println(example.getColIndex(37));
        System.out.println(Arrays.toString(example.getRow(example.getRowIndex(72))));
        System.out.println(Arrays.toString(example.getCol(example.getColIndex(38))));
        System.out.println(example.getRowIndex(5));
        System.out.println(example.getColIndex(32));
        System.out.println(example.getSubGridIndex(40));
        System.out.println(Arrays.toString(example.getSubGrid(example.getSubGridIndex(40))));
        example.readSudoku("test.txt");

        SudokuStructure example2 = new SudokuStructure(false);
        example2.readSudoku("test.txt");
        example2.printSudoku();

    }
}