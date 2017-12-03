package be.dylanvanassche.maze.model;

public class WeHaveAWinner extends Exception {
	public WeHaveAWinner() {
		super();
	}
	
	public WeHaveAWinner(String msg) {
		super(msg);
	}
}
