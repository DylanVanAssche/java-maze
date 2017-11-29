// Dylan Van Assche - 3 ABA EI

package be.dylanvanassche.maze.model;

public class Square {
	private SquareType content;

	public SquareType getContent() {
		return content;
	}

	public void setContent(SquareType content) {
		this.content = content;
	}
	
	public Square(SquareType type) {
		this.setContent(type);
	}
	
	public String toString() {
		if(this.isGold()) {
			return "G";
		}
		else if(this.isWall()) {
			return "#";
		}
		else {
			return " ";
		}
	}
	
	public boolean isGold() {
		return getContent() == SquareType.GOLD;
	}
	
	public boolean isWall() {
		return getContent() == SquareType.WALL;
	}
	
	public boolean isFree() {
		return getContent() == SquareType.FREE;
	}
}