package sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cell {
	
	Integer index;
	Integer value;
	int[] relevantRow = IndexProvider.rowIndexMap.get(IndexProvider.rowIndex[index]);
	int[] relevantCol = IndexProvider.colIndexMap.get(IndexProvider.colIndex[index]);
	int[] relevantGrid = IndexProvider.gridIndexMap.get(IndexProvider.gridIndex[index]);
	ArrayList<Integer> possibleNumbers = value.equals(0) ? getMissingNumbers() : null;
	boolean isInstantSolvable;
	
	public Cell(Integer index,Integer value) {
		this.index = index;
		this.value = value;
	}
	
	private ArrayList<Integer> getMissingNumbers() {
		getMissingNumberFromArray(relevantRow);
		return null;
	}
	
	private ArrayList<Integer> getMissingNumberFromArray(int[] input) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			if (input[i] != 0) {
				result.add(input[i]);
			}
		}
		return result;
	}
	
	
	

}
