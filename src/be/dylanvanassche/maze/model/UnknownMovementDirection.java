// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

public class UnknownMovementDirection extends IllegalArgumentException {
	public UnknownMovementDirection() {
		super();
	}
	
	public UnknownMovementDirection(String msg) {
		super(msg);
	}
}
