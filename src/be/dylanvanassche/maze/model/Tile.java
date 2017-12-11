// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public abstract class Tile {
	public static final int tileSize = 3; // define
	private Square[][] squares = new Square[tileSize][tileSize];
	private int currentSquareIndexRow = 0;

	private int currentSquareIndexColumn = 0;

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public int getCurrentSquareIndexRow() {
		return currentSquareIndexRow;
	}

	public void setCurrentSquareIndexRow(int currentSquareIndexRow) {
		this.currentSquareIndexRow = currentSquareIndexRow;
	}

	public int getCurrentSquareIndexColumn() {
		return currentSquareIndexColumn;
	}

	public void setCurrentSquareIndexColumn(int currentSquareIndexColumn) {
		this.currentSquareIndexColumn = currentSquareIndexColumn;
	}
	
	/*
	 *  @brief: Transpose a matrix
	 *  @description: Linear Algebra: rotating a matrix requires to transpose the matrix and then swap the columns
	 *  Algorithm: Only 1/2 of the matrix needs to be swapped (otherwise double swapping)
	 *  @returns: transposed matrix
	 */
	private Square[][] transpose(Square[][] squaresTemp) {
		for ( int i = 0; i < tileSize; i++ ) {
	        for ( int j = i + 1; j < tileSize; j++ ) {
	        	Square tmp = squaresTemp[i][j];
	        	squaresTemp[i][j] = squaresTemp[j][i];
	        	squaresTemp[j][i] = tmp;
	        }
	    }
		return squaresTemp;
	}
	
	/*
	 *  @brief: Swap the squares matrix columns
	 *  @description: Linear Algebra: rotating a matrix requires to transpose the matrix and then swap the columns
	 *  Algorithm: Only 1/2 of the matrix needs to be swapped (otherwise double swapping)
	 *  @returns: swapped columns matrix
	 */
	private Square[][] swap(Square[][] squaresTemp) {
		for ( int i = 0; i < tileSize; i++ ) {
	        for ( int j = 0; j < tileSize/2; j++ ) {
	            Square tmp = squaresTemp[i][j];
	            squaresTemp[i][j] = squaresTemp[i][tileSize-1-j];
	            squaresTemp[i][tileSize-1-j] = tmp;
	        }
	    }
		return squaresTemp;
	}
	
	/*
	 * @brief: rotates the squares matrix by 90 degrees
	 */
	public void rotate(long rotationAmount) {
		Square[][] squaresTemp = new Square[tileSize][tileSize];
		squaresTemp = this.getSquares();
		for(int rotations = 0; rotations < rotationAmount; rotations++) 
		{
			squaresTemp = this.transpose(squaresTemp);
			squaresTemp = this.swap(squaresTemp);
		}
		this.setSquares(squaresTemp);
	}
	
	public String toString() {
		String tileString = "";
		for(int i=0; i<tileSize; i++) {
			for(int j=0; j<tileSize; j++) {
				tileString += this.getSquares()[i][j];
			}
			if(i < tileSize) {
				tileString += "\n";
			}
		}
		// Remove obsolete character "\n" at the end
		tileString = tileString.substring(0,tileString.length()-1); 
		return tileString;
	}
	
	public Square getMiddleSquare() {
		return this.getSquares()[tileSize/2][tileSize/2];
	}
	
	/*
	 * @brief: set the Player in the middle of Tile on a specific Square.
	 */
	public void enableGold() {
		this.getMiddleSquare().setContent(SquareType.GOLD);
	}
	
	/*
	 * @brief: set the Player in the middle of Tile on a specific Square. The new Position is calculated and returned.
	 * @return: Position
	 */
	public Position enablePlayer() {
		this.getMiddleSquare().setContent(SquareType.PLAYER);
		return new Position(this.getSquares()[tileSize/2][tileSize/2], this);
	}

	/*
	 * @brief: iterator for all Squares in the Tile
	 * @return: Square
	 */
	public Square nextSquare() {
		int indexRow = this.getCurrentSquareIndexRow();
		int indexColumn = this.getCurrentSquareIndexColumn();
		
		if(indexColumn < tileSize-1) {
			this.setCurrentSquareIndexColumn(indexColumn + 1);
		}
		else {
			this.setCurrentSquareIndexColumn(0);
			
			if(indexRow < tileSize-1) {
				this.setCurrentSquareIndexRow(indexRow + 1);
			}
			else {
				this.setCurrentSquareIndexRow(0);
			}
		}
		return this.getSquares()[indexRow][indexColumn];
	}
	
	public SquareIndex getPositionFromSquare(Square square) throws SquareUnavailable {
		int rowIndex = -1;
		int columnIndex = -1;
		for(int i = 0; i < tileSize; i++) {
			for(int j = 0; j < tileSize; j++) {
				if(this.getSquares()[i][j] == square) {
					rowIndex = i;
					columnIndex = j;
					break;
				}
			}
		}
		if(rowIndex == -1 || columnIndex == -1) {
			throw new SquareUnavailable("This Square isn't located in the current Tile!");
		}
		return new SquareIndex(rowIndex, columnIndex);
	}
	
	public Square getSquareFromPosition(SquareIndex squareIndex) throws ArrayIndexOutOfBoundsException {
		return this.getSquares()[squareIndex.getRowIndex()][squareIndex.getColumnIndex()];
	}
}
