// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public class Player {
	private String name;
	private Position position;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Player() {
	}
}
