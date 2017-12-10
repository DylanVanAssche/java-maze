// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

public class TileUnavailable extends IllegalArgumentException {
	public TileUnavailable(String msg) {
		super(msg);
	}
	
	public TileUnavailable() {
		super();
	}
}
