// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public class Position {
	private Square square;
	private Tile tile;
	
	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Position(Square square, Tile tile) {
		this.setSquare(square);
		this.setTile(tile);
	}
	
	public String toString() {
		return this.getSquare() + " from " + this.getTile();
	}
	
}
