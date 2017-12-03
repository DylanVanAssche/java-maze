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
		
		this.getTiles().get((int)(Math.random()*4*Math.pow(mazeSize, 2))).enableGold();
						
		// create new player
		this.setPlayer(new Player(playerName, generateRandomCenteredPosition()));
		this.getSquareAtPosition(this.getPlayer().getPosition()).setContent(SquareType.PLAYER);
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
	 * @brief: generates a random Position in the centre of a random Tile
	 * @return: Position
	 */
	private Position generateRandomCenteredPosition() {
		int positionX = tileSize/2;
		int positionY = tileSize/2;
		for(int i=0; i < (int)(Math.random()*(mazeSize*mazeSize-1)); i++) 
		{
			if(Math.random() < 0.5) {
				positionX += tileSize;
			}
			else 
			{
				positionY += tileSize;
			}
		}
		return new Position(positionX, positionY);
	}
	
	/*
	 * @brief: Checks which square will be on the next position
	 * @return: SquareType
	 */
	private Square getSquareAtPosition(Position nextPosition) {
		// Locate the tile in the maze
		/*
		 * Algorithm: 
		 * 	1) Start at coordinate 0
		 *  2) Retrieve position
		 *  3) While position - tileSize > 0 increase coordinate
		 *  4) Break when position < 0
		 */
		System.out.println("POS: " + nextPosition);		
		
		// Search for tile X coordinate
		int tileX = 0;
		int tempValueX = nextPosition.getX();
		while(true) {
			tempValueX = tempValueX - tileSize;
			if(tempValueX < 0)
			{
				break;
			}
			tileX++;
		}
		
		// Search for tile Y coordinate
		int tileY = 0;
		int tempValueY = nextPosition.getY();
		while(true) {
			tempValueY = tempValueY - tileSize;
			if(tempValueY < 0)
			{
				break;
			}
			tileY++;
		}
		
		System.out.println("TILE: " + new Position(tileX, tileY));
		
		// Locate the square in the tile
		/*
		 * Algorithm: 
		 * 	1) Translate Tile coordinate to Square coordinate: offset = coordinateTile * tileSize
		 *  2) Substract the tileCoordinateOffset from our position: coordinateSquareWithinTile = position - offset
		 */
		int squareX = nextPosition.getX() - tileX*tileSize;
		int squareY = nextPosition.getY() - tileY*tileSize;
		
		System.out.println("SQUARE: " + new Position(squareX, squareY));
		
		// Retrieve the tile and the Square based on the coordinates
		Tile nextTile = this.getTiles().get(tileY*mazeSize*2 + tileX);
		Square nextSquare = nextTile.getSquares()[squareX][squareY];
		
		return nextSquare;
	}
	
	/*
	 * @brief: iterator to retrieve the tiles in the maze
	 * @return: Tile
	 */
	public Tile nextTile() {
		int index = this.getCurrentTileIndex();
		if(index < Math.pow(mazeSize, 2)) 
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
	 * @brief: moves the player one square
	 * @throws: UnknownMovementDirection in case a movement is requested that isn't valid
	 */
	public void movePlayer(MovementType movement) throws UnknownMovementDirection, WeHaveAWinner, BadMovementDirection {
		Position nextPos = new Position(this.getPlayer().getPosition().getX(), this.getPlayer().getPosition().getY());
		System.out.println("Now:" + nextPos);
		
		// Calculate next position
		switch(movement) 
		{
		case UP:
			nextPos.setY(nextPos.getY() + 1);
			break;
		case DOWN:
			nextPos.setY(nextPos.getY() - 1);
			break;
		case RIGHT:
			nextPos.setX(nextPos.getX() + 1);
			break;
		case LEFT:
			nextPos.setX(nextPos.getX() - 1);
			break;
		default:
			throw new UnknownMovementDirection();
		}
		System.out.println("New:" + nextPos);
		
		if(this.getSquareAtPosition(nextPos).isFree() == true) 
		{
			System.out.println("FREE MOVE");
			this.getSquareAtPosition(this.getPlayer().getPosition()).setContent(SquareType.FREE);
			this.getPlayer().setPosition(nextPos);
			this.getSquareAtPosition(nextPos).setContent(SquareType.PLAYER);
		}
		else if(this.getSquareAtPosition(nextPos).isGold() == true) 
		{
			System.out.println("GOLD MOVE");
			this.getSquareAtPosition(this.getPlayer().getPosition()).setContent(SquareType.FREE);
			this.getPlayer().setPosition(nextPos);
			this.getSquareAtPosition(nextPos).setContent(SquareType.PLAYER);
			throw new WeHaveAWinner("You won!\nCongratulations!");
		}
		else 
		{
			System.out.println("WALL MOVE");
			throw new BadMovementDirection("You can't move through walls!");
		}
	}
}
