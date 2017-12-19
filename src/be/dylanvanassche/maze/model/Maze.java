// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.model;

public class Maze {
	public static int mazeSize = 2; // define n
	public static final int tileSize = 3;
	private int currentTileIndex = 0;
	private int currentTileIndexRow = 0;
	private int currentTileIndexColumn = 0;
	private Tile[][] tiles = new Tile[mazeSize*2][mazeSize*2]; //init array
	private Player player;

	public int getCurrentTileIndex() {
		return currentTileIndex;
	}

	public void setCurrentTileIndex(int currentTileIndex) {
		this.currentTileIndex = currentTileIndex;
	}

	public int getCurrentTileIndexRow() {
		return currentTileIndexRow;
	}

	public void setCurrentTileIndexRow(int currentTileIndexRow) {
		this.currentTileIndexRow = currentTileIndexRow;
	}

	public int getCurrentTileIndexColumn() {
		return currentTileIndexColumn;
	}

	public void setCurrentTileIndexColumn(int currentTileIndexColumn) {
		this.currentTileIndexColumn = currentTileIndexColumn;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public static int getMazeSize() {
		return mazeSize;
	}

	public static void setMazeSize(int mazeSize) {
		Maze.mazeSize = mazeSize;
	}

	/*
	 * @brief: constructs a new random Maze
	 */
	public Maze() {
		// n^2 tiles
		for(int i=0; i<mazeSize*2; i++) {
			for(int j=0; j<mazeSize*2; j++) {
				this.getTiles()[i][j] = this.generateRandomTile();
			}
		}

		// create new player and enable gold and void collision
		this.setPlayer(new Player());
		this.getTiles()[(int)(Math.random()*2*mazeSize)][(int)(Math.random()*2*mazeSize)].enableGold();
		Tile target = this.getTiles()[(int)(Math.random()*2*mazeSize)][(int)(Math.random()*2*mazeSize)];
		while(target.getMiddleSquare().getContent() == SquareType.GOLD) { // It's never possible that the content is WALL
			target = this.getTiles()[(int)(Math.random()*2*mazeSize)][(int)(Math.random()*2*mazeSize)];
		}
		Position newPlayerPosition = target.enablePlayer();
		this.getPlayer().setPosition(newPlayerPosition);
	}
	
	public String toString() {
		String mazeString = "";
		for(int i=0; i<mazeSize*2; i++) {
			for(int j=0; j<mazeSize*2; j++) {
				mazeString += this.getTiles()[i][j];
			}
			if(i < tileSize) {
				mazeString += "\n";
			}
		}
		// Remove obsolete character "\n" at the end
		mazeString = mazeString.substring(0,mazeString.length()-1); 
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
	 * @brief: iterator for all Tiles in the Maze
	 * @return: Square
	 */
	public Tile nextTile() {
		int indexRow = this.getCurrentTileIndexRow();
		int indexColumn = this.getCurrentTileIndexColumn();
		
		if(indexColumn < 2*mazeSize-1) {
			this.setCurrentTileIndexColumn(indexColumn + 1);
		}
		else {
			this.setCurrentTileIndexColumn(0);
			
			if(indexRow < 2*mazeSize-1) {
				this.setCurrentTileIndexRow(indexRow + 1);
			}
			else {
				this.setCurrentTileIndexRow(0);
			}
		}
		return this.getTiles()[indexRow][indexColumn];
	}
	
	/*
	 * @brief: returns the next Tile based on a Movement of the Player
	 * @description: ArrayList of Tiles, the RIGHT and LEFT Tiles are next to the current Tile. 
	 * The UP and DOWN Tiles are 1 Math.pow(2*mazeSize, 2) away from the current Tile
	 * @return: Tile
	 */
	private Tile nextTileFromMovement(MovementType movement) throws UnknownMovementDirection, BadMovementDirection {
		// Retrieve the tileIndex by searching it in the ArrayList of Tiles
		int oldTileIndexRow = -1;
		int oldTileIndexColumn = -1;
		for(int i=0; i<mazeSize*2; i++) {
			for(int j=0; j<mazeSize*2; j++) {
				if(this.getPlayer().getPosition().getTile() == this.getTiles()[i][j]) {
					oldTileIndexRow = i;
					oldTileIndexColumn = j;
					break;
				}
			}
		}
		SquareIndex tileIndex = new SquareIndex(oldTileIndexRow, oldTileIndexColumn);

		switch(movement) 
		{
		case LEFT:
			tileIndex.setColumnIndex(tileIndex.getColumnIndex() - 1);
			break;
		case RIGHT:
			tileIndex.setColumnIndex(tileIndex.getColumnIndex() + 1);
			break;
		case DOWN:
			tileIndex.setRowIndex(tileIndex.getRowIndex() + 1);
			break;
		case UP:
			tileIndex.setRowIndex(tileIndex.getRowIndex() - 1);
			break;
		default:
			throw new UnknownMovementDirection("MovementType is unknown!");
		}
		
		try {
			return this.getTiles()[tileIndex.getRowIndex()][tileIndex.getColumnIndex()];
		}
		catch(ArrayIndexOutOfBoundsException exception) {
			throw new BadMovementDirection("You can't move through walls! (2)");
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
			return nextTile.getSquareFromPosition(oldSquarePosition);
		}
		catch(SquareUnavailable exception) {
			throw new UnknownMovementDirection(exception.getMessage());
		}
	}
	
	private void updatePositions(Square newSquare, Square oldSquare, Tile newTile) throws WeHaveAWinner, BadMovementDirection {
		if(newSquare.isFree() == true) 
		{
			this.getPlayer().setPosition(new Position(newSquare, newTile));
			oldSquare.setContent(SquareType.FREE); // Player can only be on FREE Squares
			newSquare.setContent(SquareType.PLAYER);
		}
		else if(newSquare.isGold() == true) 
		{
			this.getPlayer().setPosition(new Position(newSquare, newTile));
			oldSquare.setContent(SquareType.FREE); // Player can only be on FREE Squares
			newSquare.setContent(SquareType.PLAYER);
			throw new WeHaveAWinner("You won!\nCongratulations " + this.getPlayer().getName() + "!");
		}
		else 
		{
			throw new BadMovementDirection("You can't move through walls! (3)");
		}
	}

	/*
	 * @brief: moves the player one square
	 * @throws: UnknownMovementDirection in case a movement is requested that isn't valid
	 */
	public void movePlayer(MovementType movement) throws UnknownMovementDirection, WeHaveAWinner, BadMovementDirection {
		Square oldSquare = this.getPlayer().getPosition().getSquare();
		try 
		{
			// Calculate next position
			SquareIndex oldSquarePosition = this.getPlayer().getPosition().getTile().getPositionFromSquare(oldSquare);
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

			try {
				Square newSquare = this.getPlayer().getPosition().getTile().getSquareFromPosition(oldSquarePosition);
				this.updatePositions(newSquare, oldSquare, this.getPlayer().getPosition().getTile());	
			}
			// We're moving out the current Tile, calculating the next Tile is required!
			catch(ArrayIndexOutOfBoundsException exception) {
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
