package sudoku;

import java.util.Stack;

public class SudokuSolver {

	public SudokuSolver() {
	}

	public static void solve() {
		Stack<Integer> turns = new Stack<>();
		Sudoku sudoku = new Sudoku("test.txt");
		sudoku.fillInstantSolvableCells();
		sudoku.printSudoku();
		EditableCell currentCell = sudoku.getNextEditableCell();
		while (currentCell != null) {
			if (currentCell.guessNumber()) {
				turns.push(currentCell.index);
			} else {
				// backtracking
				currentCell = sudoku.getCell(turns.pop());
				currentCell.revokeGuess(currentCell.getValue());
			}
			currentCell = sudoku.getNextEditableCell();
		}
		sudoku.printSudoku();
	}

}
