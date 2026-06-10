package sudoku;

public class Main {
    public static void main(String[] args) {

    	long startTime = System.nanoTime();
    	SudokuSolver.solve();
    	long endTime = System.nanoTime();
    	long duration = ((endTime - startTime)/1000000);
    	System.out.println("Time taken for whole program: " + duration + " milliseconds");
    	
//    	long startTime = System.nanoTime();
//    	Sudoku example = new Sudoku("test.txt");
//    	example.printSudoku();
//    	long endTime = System.nanoTime();
//    	long duration = ((endTime - startTime)/1000000);
//    	System.out.println("Time taken for whole program: " + duration + " milliseconds");
    }
}