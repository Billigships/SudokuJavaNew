package sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku {

	public static Cell[] Sudoku = new Cell[81];
	public static Integer instantSolvableCount; 

	public Sudoku(String inputFile) {
		this.importAndInitializeSudoku(inputFile);
		this.setDependencies();
		this.updateCellStatuses();
	}

	private void setDependencies() {
		for (Cell cell : Sudoku) {
			if (cell instanceof EditableCell eCell) {
				eCell.setDependencies(Sudoku);
			}
		}
	}

	private void updateCellStatuses() {
		for (Cell cell : Sudoku) {
			if (cell instanceof EditableCell eCell) {
				eCell.setMissingNumbers();
			}
		}
	}

	public void fillInstantSolvableCells() {
		for (Cell cell : Sudoku) {
			if (cell instanceof EditableCell eCell) {
				if (eCell.isInstantSolvable) {
					eCell.guessNumber();
					eCell.isInstantSolvable = false;
				}
			}
		}
	}

	private void importAndInitializeSudoku(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int importedNumber;
			int i = 0;
			while ((importedNumber = br.read()) != -1) {
				importedNumber = Character.getNumericValue(importedNumber);
				Sudoku[i] = importedNumber == 0 ? new EditableCell(i, importedNumber) : new ImmutableCell(i, importedNumber);
				i++;
			}
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
		}
	}

	public EditableCell getNextEditableCell() {
		for (Cell cell : Sudoku) {
			if (cell instanceof EditableCell eCell && cell.getValue().equals(0)) {
				return eCell;
			}
		}
		return null;
	}

	public EditableCell getCell(Integer input) {
		if (Sudoku[input] instanceof EditableCell eCell) {
			return eCell;
		}
		return null;
	}

	public void printSudoku() {
		for (int j = 0; j <= 80; j++) {
			System.out.print(Sudoku[j].getValue() + " ");
			if ((j + 1) % 9 == 0) {
				System.out.println();
			}
		}
	}

}
