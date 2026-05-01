package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SudokuStructure {
    public int[] sudoku = new int[81];
    private Map<Integer, int[]> rowMap = fillRow();
    private Map<Integer, int[]> columnMap = fillColumn();
    private Map<Integer, int[]> gridMap = fillGrid();
    

    public SudokuStructure(boolean example) {
        if (example) {
            this.createExample();
        }
    }

    private Map<Integer, int[]> fillGrid() {
    	HashMap<Integer, int[]> grid = new HashMap<>();
    	for (int i = 0; i < 9; i++) {
    		grid.put(i, getSubGrid(i));
		}
		return grid;
	}

	private Map<Integer, int[]> fillColumn() {
		HashMap<Integer, int[]> column = new HashMap<>();
    	for (int i = 0; i < 9; i++) {
    		column.put(i, getCol(i));
		}
		return column;
	}

	private Map<Integer, int[]> fillRow() {
    	HashMap<Integer, int[]> row = new HashMap<>();
    	for (int i = 0; i < 9; i++) {
			row.put(i, getRow(i));
		}
		return row;
	}

	private void createExample() {
        for (int i = 1; i <= 81; i++) {
            sudoku[i - 1] = i;
        }
    }

    public void printSudoku() {
        for (int j = 0; j <= 80; j++) {
            System.out.print(this.sudoku[j] + " ");
            if ((j + 1) % 9 == 0) {
                System.out.println();
            }
        }
    }

    public int getRowIndex(int elIndex) {
        return elIndex / 9;
    }

    public int[] getRow(int row) {
        return Arrays.copyOfRange(this.sudoku, (row * 9), row * 9 + 9);
    }

    public int getColIndex(int elIndex) {
        return elIndex % 9;
    }

    public int[] getCol(int col) {
        int[] result = new int[9];
        int j = 0;
        for (int i = col; i <= col + (8 * 9); i += 9) {
            result[j] = this.sudoku[i];
            j++;
        }
        return result;
    }

    public int getSubGridIndex(int gridIndex) {
        int row = (getRowIndex(gridIndex) / 3) * 3;
        int col = (getColIndex(gridIndex) / 3) * 3;
        return col + (row * 9);
    }

    public int[] getSubGrid(int index) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j = 0; j <= 18; j += 9) {
            for (int i = 0; i < 3; i++) {
                list.add(this.sudoku[j + index + i]);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public void readSudoku(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int character;
            int i = 0;
            while ((character = br.read()) != -1) {
                character = Character.getNumericValue(character);
//                System.out.print(character + " ");
                this.sudoku[i] = character;
                i++;
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }
//  public boolean checkIfOneSolved(int index){
//  Set<Integer> set = new HashSet<>();
//  int[] row = getRow(index);
//  int[] col = getCol(index);
//  int[] subGrid = getSubGrid(index);
//
//
//}


//    public boolean checkIfSolved() {
//
//    }

}
