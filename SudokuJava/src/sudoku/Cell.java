package sudoku;

import java.util.ArrayList;
import java.util.HashSet;

public class Cell {
	
	Integer index;
	Integer value;
	int[] relevantRow = IndexProvider.rowIndexMap.get(IndexProvider.rowIndex[index]);
	int[] relevantCol = IndexProvider.colIndexMap.get(IndexProvider.colIndex[index]);
	int[] relevantGrid = IndexProvider.gridIndexMap.get(IndexProvider.gridIndex[index]);
	Integer[] possibleNumbers; // TODO sollte eigentlich erst später durchgeführt werden, wenn das Sudoku gesetzt wurde
	boolean isInstantSolvable;
	
	public Cell(Integer index, Integer value) {
		this.index = index;
		this.value = value;
	}
	
	private Integer[] getMissingNumbers() {
		// TODO for better performance it should first check if a row for example only returns one number. 
		// Then this number is automatically the only one possible and the other lists dont have to be checked
		HashSet<Integer> result = new HashSet<>();
		ArrayList<Integer> row = getMissingNumbersFromArray(Sudoku.relevantRow);
		ArrayList<Integer> col = getMissingNumbersFromArray(relevantRow);
		ArrayList<Integer> grid = getMissingNumbersFromArray(relevantRow);
		result.addAll(row);
		result.addAll(col);
		result.addAll(grid);
		return result.toArray(new Integer[0]);
	}
	
	private ArrayList<Integer> getMissingNumbersFromArray(int[] input) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			if (input[i] != 0) {
				result.add(input[i]);
			}
		}
		return result;
	}
	
	private void updateStatus() {
		
	}
	
	public Integer getValue() {
		return this.value;
		// Index is not needed here because this function only gets called in the Sudoku class which contains all cells
		// and therefore knows every index of the cells
	}

}
