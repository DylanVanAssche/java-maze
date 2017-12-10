// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

import be.dylanvanassche.maze.model.Tile;

public class TileStraight extends Tile {
	final private Square[][] squares = {
			{new Square(SquareType.WALL), new Square(SquareType.WALL), new Square(SquareType.WALL)}, 
			{new Square(SquareType.FREE), new Square(SquareType.FREE), new Square(SquareType.FREE)},
			{new Square(SquareType.WALL), new Square(SquareType.WALL), new Square(SquareType.WALL)}
			};

	public TileStraight() {
		super.setSquares(squares);
		super.rotate(Math.round(Math.random()*3));
	}
}