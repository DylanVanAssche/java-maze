// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

public class SquareUnavailable extends IllegalArgumentException {
	public SquareUnavailable(String msg) {
		super(msg);
	}
	
	public SquareUnavailable() {
		super();
	}
}
