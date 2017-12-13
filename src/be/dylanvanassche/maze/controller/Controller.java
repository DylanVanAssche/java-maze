// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.controller;

import be.dylanvanassche.maze.model.*;
import be.dylanvanassche.maze.view.*;

public class Controller {
	private Maze maze;
	private MainFrame mainFrame;
	private String name = "Java Maze";
	private String author = "Dylan Van Assche";
	private String version = "V0.0.1";
	
	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static void main(String[] args) throws UnknownMovementDirection, WeHaveAWinner, BadMovementDirection  {
		Controller controller = new Controller();
	}
	
	public Controller() {
		// Player name screen can be added in the future as extension
		this.setMaze(new Maze());
		this.setMainFrame(new MainFrame(this));
	}
	
	public Tile nextMazeTile() {
		return this.getMaze().nextTile();
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void exitApplication() {
		this.getMainFrame().dispose();	
	}
	
	public void newGame() {
		this.setMaze(new Maze());
		this.getMainFrame().newGame();
	}
	
	public void movePlayer(MovementType movement) throws UnknownMovementDirection, BadMovementDirection, WeHaveAWinner {
		try 
		{
			this.getMaze().movePlayer(movement);
			this.getMainFrame().updateGame();
		}
		catch(WeHaveAWinner exception) 
		{
			this.getMainFrame().updateGame(); // Enforce update when reaching gold
			throw new WeHaveAWinner(exception.getMessage());
		}
	}

	public void setPlayerName(String name) {
		this.getMaze().getPlayer().setName(name);
	}
}
