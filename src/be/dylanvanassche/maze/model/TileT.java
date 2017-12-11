// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public class TileT extends Tile {
	final private Square[][] squares = {
			{new Square(SquareType.WALL), new Square(SquareType.FREE), new Square(SquareType.WALL)}, 
			{new Square(SquareType.FREE), new Square(SquareType.FREE), new Square(SquareType.FREE)},
			{new Square(SquareType.WALL), new Square(SquareType.WALL), new Square(SquareType.WALL)}
			};
	
	public TileT() {
		super.setSquares(squares);
		super.rotate(Math.round(Math.random()*3));
	}
}
