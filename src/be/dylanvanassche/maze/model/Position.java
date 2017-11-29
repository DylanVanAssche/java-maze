// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public class Position {
	private int x = 0;
	private int y = 0;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Position(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
}
