package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class EditableCell implements Cell{
	
	//TODO callback function to other relevant Cells after a Number has changed for example
	
	Integer index;
	Integer value;
	private final int[] relevantRowIndex;
	private final int[] relevantColIndex;
	private final int[] relevantGridIndex;
	private ArrayList<Cell> relevantRow;
	private ArrayList<Cell> relevantCol;
	private ArrayList<Cell> relevantGrid;
	private ArrayList<Integer> possibleNumbers;
	boolean isInstantSolvable = false;
	
	
	public EditableCell(Integer index, Integer value) {
		this.index = index;
		this.value = value;
		this.relevantRowIndex = IndexProvider.getRowIndex(index);
		this.relevantColIndex = IndexProvider.getColIndex(index);
		this.relevantGridIndex = IndexProvider.getGridIndex(index);
	}
	
	private ArrayList<Integer> getMissingNumbers() {
		ArrayList<Integer> entries = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		HashSet<Integer> missingNumbers = new HashSet<>();
		ArrayList<Integer> result = new ArrayList<>();
		missingNumbers.addAll(getMissingNumbersFromArray(relevantRow));
		entries.removeAll(missingNumbers);
		if(entries.size() == 1) {
			this.isInstantSolvable = true;
			result.addAll(entries);
			return result;
		}
		missingNumbers.addAll(getMissingNumbersFromArray(relevantCol));
		entries.removeAll(missingNumbers);
		if(entries.size() == 1) {
			this.isInstantSolvable = true;
			result.addAll(entries);
			return result;
		}
		missingNumbers.addAll(getMissingNumbersFromArray(relevantGrid));
		entries.removeAll(missingNumbers);
		if(entries.size() == 1) {
			this.isInstantSolvable = true;
			result.addAll(entries);
			return result;
		}
		result.addAll(entries);
		return result;
	}
	
	private ArrayList<Integer> getMissingNumbersFromArray(ArrayList<Cell> input) {
		ArrayList<Integer> result = new ArrayList<>();
		for (Cell cell : input) {
			if(cell.getValue() != 0) {
				result.add(cell.getValue());
			}
		}
		return result;
	}
	
	public void setMissingNumbers() {
		this.possibleNumbers = this.getMissingNumbers();
	}
	
	public void updateCell(Integer newNumber) {
		this.possibleNumbers.remove(newNumber);
		if (this.possibleNumbers.size() == 1) {
			this.isInstantSolvable = true;
		}
	}
	
	//guess first Number and remove it
	public boolean guessNumber() {
		ArrayList<Integer> possibleNumbers = this.possibleNumbers;
		if (possibleNumbers != null && !possibleNumbers.isEmpty()) {
			this.value = possibleNumbers.removeFirst();
			System.out.println("set Cell " + this.index + " with value of " + this.value);
			updateRelevantCells(this.value);
			return true;
		}
		return false;
	}
	
	public void revokeGuess(Integer number) {
		this.value = 0;
		for (Cell cell : this.relevantRow) {
			if (cell instanceof EditableCell eCell) {
				eCell.setMissingNumbers();
			}
		}
		for (Cell cell : this.relevantCol) {
			if (cell instanceof EditableCell eCell) {
				eCell.setMissingNumbers();
			}
		}
		for (Cell cell : this.relevantGrid) {
			if (cell instanceof EditableCell eCell) {
			eCell.setMissingNumbers();
			}
		}
		System.out.println("revoked Cell " + this.index + " with Value of " + this.value);
	}
	
	private void updateRelevantCells(Integer number) {
		for (Cell cell : this.relevantRow) {
			if (cell instanceof EditableCell eCell) {
				eCell.updateCell(number);
			}
		}
		for (Cell cell : this.relevantCol) {
			if (cell instanceof EditableCell eCell) {
				eCell.updateCell(number);
			}
		}
		for (Cell cell : this.relevantGrid) {
			if (cell instanceof EditableCell eCell) {
			eCell.updateCell(number);
			}
		}
	}
	
	
	public void setDependencies(Cell[] sudoku) {
		this.relevantRow = this.getDependenciesFromArray(sudoku, relevantRowIndex);
		this.relevantCol = this.getDependenciesFromArray(sudoku, relevantColIndex);
		this.relevantGrid = this.getDependenciesFromArray(sudoku, relevantGridIndex);
	}
	
	private ArrayList<Cell> getDependenciesFromArray(Cell[] sudoku, int[] indexList) {
		ArrayList<Cell> result = new ArrayList<>();
		for (int cellIndex : indexList) {
			result.add(sudoku[cellIndex]);
		}
		return result;
	}
	
	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public Integer getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
