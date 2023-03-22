package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game,
 * specifically.
 * 
 * @author Shuaib Syed
 */

public class ClearCellGameModel extends GameModel {

	private Random randomNum;
	private int score;

	/**
	 * Constructor that sets the board relying on the super constructor.
	 * 
	 * @param rows number of rows in board
	 * @param cols number of columns in board
	 * @param random random number generator to be used during game when
	 * rows are randomly created
	 */
	public ClearCellGameModel(int rows, int cols, Random random) {
		super(rows, cols);
		this.score = 0;
		this.randomNum = random;
	}

	/**
	 * This method checks if the last row is empty, if so the game is over, returns true if it is empty, 
	 * false otherwise.
	 */
	@Override
	public boolean isGameOver() {
		return (isRowEmpty(board.length - 1))? false : true;
	}

	/**
	 * Returns the player's score.  The player should be awarded one point
	 * for each cell that is cleared.
	 * 
	 * @return player's score
	 */
	@Override
	public int getScore() {
		return this.score;
	}

	/* This method checks if the row is empty after going through each column of the row. Returns true if the row is 
	 * empty, false otherwise.
	 */
	private boolean isRowEmpty(int row) {
		boolean rowEmpty = false;
		int emptyCol = 0;
		for(int col = 0; col < getCols(); col++){
			if(getBoardCell(row, col) == BoardCell.EMPTY){
				emptyCol++;
			}
			// this checks and keep track the number of empty cells in the given row
		}
		if(emptyCol == getCols()){  
			rowEmpty = true;
		}
		// checks to see if the the number of empty cells found is equal to the length of the columns.
		return rowEmpty;
	}


	/**
	 * This method shifts the rows down one and inserts a row of random BoardCell objects at the top only if the game 
	 * is not yet over. Does nothing if the game is over.
	 */
	@Override
	public void nextAnimationStep() {
		if(isGameOver() == false) {
			for(int row = getRows() - 2; row >= 0; row--) {
				for(int col = 0; col < getCols(); col++) {
					if(getBoardCell(row, col) != BoardCell.EMPTY) {
						setBoardCell(row + 1, col, this.getBoardCell(row, col));
					}
					//shifts the objects in the rows down one by one and leaves the top one empty.
					if(row == 0) {
						setBoardCell(row, col, BoardCell.getNonEmptyRandomBoardCell(randomNum));
					}
					//sets the top row with Random objects.
				}
			}
		}
	}

	/**
	 * This method is called when the user clicks a cell on the board.
	 * If the selected cell is not empty, it will be set to BoardCell.EMPTY, 
	 * along with any adjacent cells that are the same color as this one.  
	 * (This includes the cells above, below, to the left, to the right, and 
	 * all in all four diagonal directions.)
	 * 
	 * If any rows on the board become empty as a result of the removal of 
	 * cells then those rows will "collapse", meaning that all non-empty 
	 * rows beneath the collapsing row will shift upward. 
	 * 
	 * @throws IllegalArgumentException with message "Invalid row index" for 
	 * invalid row or "Invalid column index" for invalid column.  We check 
	 * for row validity first.
	 */
	@Override
	public void processCell(int rowIndex, int colIndex) {
		int up = rowIndex - 1;
		int down = rowIndex + 1;
		int left = colIndex - 1;
		int right = colIndex + 1;

		if(rowIndex > getCols() || rowIndex < 0) {  // checker for invalid row
			throw new IllegalArgumentException("Invalid row index");
		}
		if(colIndex > getCols() || colIndex < 0) {  // checker for invalid column 
			throw new IllegalArgumentException("Invalid column index");
		}

		if(board[rowIndex][colIndex] != BoardCell.EMPTY) {
			if(up >= 0) {
				if(board[up][colIndex] == board[rowIndex][colIndex]) { 
					board[up][colIndex] = BoardCell.EMPTY;
					//  sets the cell above the target cell to EMPTY if it matches the parameter
					score++;
				}
				if(left >= 0) {
					if(board[up][left] == board[rowIndex][colIndex]) { 
						board[up][left] = BoardCell.EMPTY;
						//  sets the cell above and to the left of the target cell to EMPTY if it matches the parameter

						score++;
					}
				}
				if(right < getCols()) {
					if(board[up][right] == board[rowIndex][colIndex]) { 
						board[up][right] = BoardCell.EMPTY;
						//  sets the cell above and to the right of the target cell to EMPTY if it matches the parameter

						score++;
					}

				}
			}
			// This block covers the up directions to the target cell and increments the score when cell made EMPTY

			if(down < getRows()) {
				if(board[down][colIndex] == board[rowIndex][colIndex]) { 
					board[down][colIndex] = BoardCell.EMPTY;
					//  sets the cell bellow the target cell to EMPTY if it matches the parameter

					score++;
				}
				if(left >= 0) {
					if(board[down][left] == board[rowIndex][colIndex]) { 
						board[down][left] = BoardCell.EMPTY;
						// sets the cell bellow and to the left of the target cell to EMPTY if it matches the parameter

						score++;
					}
				}
				if(right < getCols()) {
					if(board[down][right] == board[rowIndex][colIndex]) { 
						board[down][right] = BoardCell.EMPTY;
						// sets the cell bellow and to the right of the target cell to EMPTY if it matches the parameter

						score++;
					}	
				}
			}

			// this block covers the down directions corresponding with the target cell

			if(left > 0) {
				if(board[rowIndex][left] == board[rowIndex][colIndex]) { 
					board[rowIndex][left] = BoardCell.EMPTY;
					// sets the cell to the left of the target cell to EMPTY if it matches the parameter

					score++;
				}
			}
			if(right < getCols()) {
				if(board[rowIndex][right] == board[rowIndex][colIndex]) { 
					board[rowIndex][right] = BoardCell.EMPTY;
					// sets the cell to the right of the target cell to EMPTY if it matches the parameter
					score++;
				}
			}
			if(board[rowIndex][colIndex] == board[rowIndex][colIndex]) { 
				board[rowIndex][colIndex] = BoardCell.EMPTY;
				// sets the target cell to EMPTY if it matches the parameter

				score++;
			}
			// this block covers the left and right directions corresponding to the target cell
		}
		for(int row = getRows()-2; row > 0; row--) {
			if(isRowEmpty(row) == true){
				for(int breakPoint = row; breakPoint < getRows()-1; breakPoint++){
					for(int col = 0; col < getCols(); col++){
						board[breakPoint][col] = board[breakPoint+1][col];
						board[breakPoint+1][col] = BoardCell.EMPTY;
					}
				}
			}
		}
		//If there are any empty rows then the non-empty rows below them will get shifted up.
	}

}

