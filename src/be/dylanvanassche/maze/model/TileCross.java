// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public class TileCross extends Tile {
	final private Square[][] squares = {
			{new Square(SquareType.WALL), new Square(SquareType.FREE), new Square(SquareType.WALL)}, 
			{new Square(SquareType.FREE), new Square(SquareType.FREE), new Square(SquareType.FREE)},
			{new Square(SquareType.WALL), new Square(SquareType.FREE), new Square(SquareType.WALL)}
			};

	public TileCross() {
		super.setSquares(squares);
		super.rotate(Math.round(Math.random()*3));
	}
}
