package be.dylanvanassche.maze.model;

public class TileBend extends Tile {
	final private Square[][] squares = {
			{new Square(SquareType.WALL), new Square(SquareType.FREE), new Square(SquareType.WALL)}, 
			{new Square(SquareType.FREE), new Square(SquareType.FREE), new Square(SquareType.WALL)},
			{new Square(SquareType.WALL), new Square(SquareType.WALL), new Square(SquareType.WALL)}
			};

	public TileBend() {
		super.setSquares(squares);
		super.rotate(Math.round(Math.random()*3));
	}
}
