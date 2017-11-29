// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public abstract class Tile {
	public static final int tileSize = 3; // define
	private Square[][] squares = new Square[tileSize][tileSize];

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
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
	
	public Tile() {
		// Choose random rotation when at construction time
		this.rotate(Math.round(Math.random()*3));
	}
	
	public String toString() {
		String tileString = "";
		for(int i=0; i<tileSize; i++) {
			for(int j=0; j<tileSize; j++) {
				tileString += this.getSquares()[i][j];
			}
			tileString += "\n";
		}
		return tileString;
	}
}
