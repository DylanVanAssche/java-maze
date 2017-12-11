// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

import java.util.ArrayList;
import java.util.List;

public class Maze {
	public static final int mazeSize = 2; // define n
	public static final int tileSize = 3;
	private int currentTileIndex = 0;
	private List<Tile> tiles = new ArrayList<Tile>(); // easy to populate a gridlayout using nextTile()
	private Player player;

	public int getCurrentTileIndex() {
		return currentTileIndex;
	}

	public void setCurrentTileIndex(int currentTileIndex) {
		this.currentTileIndex = currentTileIndex;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/*
	 * @brief: constructs a new random Maze
	 */
	public Maze(String playerName) {
		// n^2 tiles
		for(int i=0; i<4*Math.pow(mazeSize, 2); i++) 
		{
			this.getTiles().add(this.generateRandomTile());
		}

		// create new player and enable gold
		this.setPlayer(new Player(playerName));
		this.getTiles().get((int)(Math.random()*4*Math.pow(mazeSize, 2))).enableGold();
		Tile target = this.getTiles().get((int)(Math.random()*4*Math.pow(mazeSize, 2)));
		while(target.getMiddleSquare().getContent() == SquareType.GOLD) { // It's never possible that the content is WALL
			System.out.println("Collision GOLD and PLAYER");
			target = this.getTiles().get((int)(Math.random()*4*Math.pow(mazeSize, 2)));
		}
		Position newPlayerPosition = this.getTiles().get((int)(Math.random()*4*Math.pow(mazeSize, 2))).enablePlayer();
		this.getPlayer().setPosition(newPlayerPosition);
	}

	public String toString() {
		String mazeString = "";
		for(int i=0; i<4*Math.pow(mazeSize, 2); i++) 
		{
			mazeString += this.getTiles().get(i).toString() + "\n";
		}
		return mazeString;
	}

	/*
	 * @brief: generate a random type of tile
	 * @return: Tile
	 */
	private Tile generateRandomTile() {
		double random = Math.random();
		if(random < 0.25) {
			return new TileBend();
		}
		else if(random < 0.50) {
			return new TileCross();
		}
		else if(random < 0.75) {
			return new TileT();
		}
		return new TileStraight();
	}

	/*
	 * @brief: iterator to retrieve the tiles in the maze
	 * @return: Tile
	 */
	public Tile nextTile() {
		int index = this.getCurrentTileIndex();
		if(index < Math.pow(2*mazeSize, 2)-1) 
		{
			this.setCurrentTileIndex(index + 1);
		}
		else 
		{
			this.setCurrentTileIndex(0);
		}
		return this.getTiles().get(index);
	}
	
	/*
	 * @brief: returns the next Tile based on a Movement of the Player
	 * @description: ArrayList of Tiles, the RIGHT and LEFT Tiles are next to the current Tile. 
	 * The UP and DOWN Tiles are 1 Math.pow(2*mazeSize, 2) away from the current Tile
	 * @return: Tile
	 */
	private Tile nextTileFromMovement(MovementType movement) throws TileUnavailable, UnknownMovementDirection, BadMovementDirection {
		// Retrieve the tileIndex by searching it in the ArrayList of Tiles
		int tileIndex = -1;
		for(int i=0; i < Math.pow(2*mazeSize, 2)-1; i++) {
			if(this.getPlayer().getPosition().getTile() == this.getTiles().get(i)) {
				tileIndex = i;
			}
		}
		if(tileIndex == -1) {
			throw new TileUnavailable("This Tile isn't located in the current Maze!");
		}
		System.out.println(tileIndex);
		System.out.println(this.getTiles().get(tileIndex));
		// Calculate the new tileIndex
		switch(movement) 
		{
		case LEFT:
			tileIndex -= 1;
			break;
		case RIGHT:
			tileIndex += 1;
			break;
		case DOWN:
			tileIndex += 2*mazeSize;
			break;
		case UP:
			tileIndex -= 2*mazeSize;
			break;
		default:
			throw new UnknownMovementDirection();
		}
		
		System.out.println(tileIndex);
		System.out.println(this.getTiles().get(tileIndex));
		try {
			return this.getTiles().get(tileIndex);
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			System.out.println("PLAYER MOVE TO WALL");
			throw new BadMovementDirection("You can't move through walls!");
		}
	}
	
	/*
	 * @brief: returns the Square for the nextTile based on the Movement from the Player
	 * @description: nextTileFromMovement() needs to be called first to retrieve the nextTile. 
	 * This is only needed for movement that goes outside the current Tile of the Player.
	 * @return: Square
	 */
	private Square nextSquareFromMovement(MovementType movement, Tile nextTile) throws UnknownMovementDirection {
		Square oldSquare = this.getPlayer().getPosition().getSquare();
		try 
		{
			SquareIndex oldSquarePosition = this.getPlayer().getPosition().getTile().getPositionFromSquare(oldSquare);
			// Calculate the new squareIndex
			System.out.println("OLD:" + oldSquarePosition);
			switch(movement) 
			{
			case RIGHT:
				oldSquarePosition.setColumnIndex(0);
				break;
			case LEFT:
				oldSquarePosition.setColumnIndex(tileSize-1);
				break;
			case DOWN:
				oldSquarePosition.setRowIndex(0);
				break;
			case UP:
				oldSquarePosition.setRowIndex(tileSize-1);
				break;
			default:
				throw new UnknownMovementDirection("MovementType is unknown!");
			}
			
			// Return the next Square
			System.out.println("NEW:" + oldSquarePosition);
			return nextTile.getSquareFromPosition(oldSquarePosition);
		}
		catch(SquareUnavailable exception) {
			throw new UnknownMovementDirection(exception.getMessage());
		}
	}
	
	private void updatePositions(Square newSquare, Square oldSquare, Tile newTile) throws WeHaveAWinner, BadMovementDirection {
		System.out.println("NEW SQUARE:" + newSquare);
		System.out.println("OLD SQUARE:" + oldSquare);
		if(newSquare.isFree() == true) 
		{
			System.out.println("PLAYER MOVES FREELY");
			this.getPlayer().setPosition(new Position(newSquare, newTile));
			oldSquare.setContent(SquareType.FREE); // Player can only be on FREE Squares
			newSquare.setContent(SquareType.PLAYER);
		}
		else if(newSquare.isGold() == true) 
		{
			System.out.println("PLAYER IS NOW ON GOLD");
			this.getPlayer().setPosition(new Position(newSquare, newTile));
			oldSquare.setContent(SquareType.FREE); // Player can only be on FREE Squares
			newSquare.setContent(SquareType.PLAYER);
			throw new WeHaveAWinner("You won!\nCongratulations!");
		}
		else 
		{
			System.out.println("PLAYER MOVES INTO WALL");
			throw new BadMovementDirection("You can't move through walls!");
		}
	}

	/*
	 * @brief: moves the player one square
	 * @throws: UnknownMovementDirection in case a movement is requested that isn't valid
	 */
	public void movePlayer(MovementType movement) throws UnknownMovementDirection, WeHaveAWinner, BadMovementDirection {
		System.out.println("Moving: " + movement);
		Square oldSquare = this.getPlayer().getPosition().getSquare();
		System.out.println(this.getPlayer().getPosition());
		try 
		{
			SquareIndex oldSquarePosition = this.getPlayer().getPosition().getTile().getPositionFromSquare(oldSquare);
			System.out.println("Old: " + oldSquarePosition);

			// Calculate next position
			/* 			   		 UP 
			 * 			      COLUMN-1
			 * 			         ^
			 * 		             |
			 * 			         |
			 * LEFT ROW-1 <------+------> ROW-1 RIGHT
			 *  		         |	
			 *                   |
			 *                   ^
			 *           	  COLUMN+1
			 *           	    DOWN		
			 */
			switch(movement) 
			{
			case LEFT:
				oldSquarePosition.setColumnIndex(oldSquarePosition.getColumnIndex() - 1);
				break;
			case RIGHT:
				oldSquarePosition.setColumnIndex(oldSquarePosition.getColumnIndex() + 1);
				break;
			case DOWN:
				oldSquarePosition.setRowIndex(oldSquarePosition.getRowIndex() + 1);
				break;
			case UP:
				oldSquarePosition.setRowIndex(oldSquarePosition.getRowIndex() - 1);
				break;
			default:
				throw new UnknownMovementDirection("MovementType is unknown!");
			}
			System.out.println("New: " + oldSquarePosition);

			try {
				Square newSquare = this.getPlayer().getPosition().getTile().getSquareFromPosition(oldSquarePosition);
				System.out.println("Square: " + newSquare);
				this.updatePositions(newSquare, oldSquare, this.getPlayer().getPosition().getTile());	
			}
			// We're moving out the current Tile, calculating the next Tile is required!
			catch(ArrayIndexOutOfBoundsException exception) {
				System.out.println("Moving out current Tile!");
				Tile newTile = this.nextTileFromMovement(movement);
				Square newSquare = this.nextSquareFromMovement(movement, newTile);
				this.updatePositions(newSquare, oldSquare, newTile);
			}
		}
		// Square isn't located in the Tile from the current Player Position:
		catch(SquareUnavailable exception) 
		{
			throw new UnknownMovementDirection(exception.getMessage());
		}
	}
}
