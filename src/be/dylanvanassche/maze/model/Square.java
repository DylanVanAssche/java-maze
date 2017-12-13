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
			return "$";
		}
		else if(this.isWall()) {
			return "#";
		}
		else if(this.isPlayer()) {
			return "X";
		}
		else {
			return " ";
		}
	}
	
	public boolean isGold() {
		return this.getContent() == SquareType.GOLD;
	}
	
	public boolean isWall() {
		return this.getContent() == SquareType.WALL;
	}
	
	public boolean isPlayer() {
		return this.getContent() == SquareType.PLAYER;
	}
	
	public boolean isFree() {
		return this.getContent() == SquareType.FREE;
	}
}