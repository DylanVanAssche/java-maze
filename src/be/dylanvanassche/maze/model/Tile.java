// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

import java.util.Random;

public abstract class Tile {
	public static final int tileSize = 3; // define
	private Square[][] squares = new Square[tileSize][tileSize];
	private RotationType rotation;

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}
	
	public RotationType getRotation() {
		return rotation;
	}

	public void setRotation(RotationType rotation) {
		
		this.rotation = rotation;
		this.executeRotation();
	}
	
	private void executeRotation() {
		Square[][] squaresTemp = new Square[tileSize][tileSize];
		for(int i=0; i<tileSize; i++)
		{
	        for(int j=tileSize-1; j>=0; j--)
	        {
	        	squaresTemp[i][j] = this.getSquares()[j][i];
	        }
	    }
		this.setSquares(squaresTemp);
	}
	
	public Tile() {
		// Choose random rotation when at construction time
		Random random = new Random();
		RotationType[] rotationTypes=RotationType.values();
		this.setRotation(rotationTypes[random.nextInt(rotationTypes.length)]);
	}
	
	// Turn 90 degrees -> row becomes column, invert String
	public String toString() {
		String tileString = "";
		// Create 0 degrees string
		for(int i=0; i<tileSize; i++) {
			for(int j=0; j<tileSize; j++) {
				tileString += this.getSquares()[i][j];
			}
			tileString += "\n";
		}
		return tileString;
	}
}
