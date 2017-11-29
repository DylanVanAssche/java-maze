package be.dylanvanassche.maze.model;

import java.util.List;

public class Maze {
	public static final int mazeSize = 2; // define n
	private int currentTileIndex = 0;
	private List<Tile> tiles; // easy to populate a gridlayout using nextTile()
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
		for(int i=0; i < 4*Math.pow(mazeSize, 2); i++) {
			this.getTiles().add(this.generateRandomTile());
		}
		// get random central position
		
		// create new player
		this.setPlayer(new Player(playerName, new Position(0,0)));
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
	
	public SquareType moveTo(Position nextPosition) {
		// Locate the tile in the maze
		double tileRow = Math.floor(nextPosition.getX()/(2*mazeSize*1.0));
		double tileColumn = Math.floor(nextPosition.getY()/(2*mazeSize*1.0));
		int tileListIndex = (int)((2*mazeSize)*tileRow + tileColumn);
		Tile nextTile = this.getTiles().get(tileListIndex);
		
		// Locate the square in the tile
		int squareRow = (int)(Math.floor(nextPosition.getX()/(2*mazeSize*1.0)) - tileRow);
		int squareColumn = (int)(Math.floor(nextPosition.getY()/(2*mazeSize*1.0)) - tileColumn);
		Square nextSquare = nextTile.getSquares()[squareRow][squareColumn];
		
		// Check what kind of square it is
		if(nextSquare.isFree() == true) // Move allowed
		{
			return SquareType.FREE;
		}
		else if(nextSquare.isGold() == true) // Don't move, we have a winner!
		{
			return SquareType.GOLD;
		}
		return SquareType.WALL; // Don't move, we hit a wall!
	}
}
