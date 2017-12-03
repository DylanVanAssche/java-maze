// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

public class BadMovementDirection extends IllegalArgumentException {
	public BadMovementDirection() {
		super();
	}
	
	public BadMovementDirection(String msg) {
		super(msg);
	}
}
