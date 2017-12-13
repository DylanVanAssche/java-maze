// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import be.dylanvanassche.maze.controller.*;
import be.dylanvanassche.maze.model.*;

public class TileView extends JPanel {
	private Controller controller;
	private Tile tile;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public TileView(final Controller c, Tile tile) {
		this.setController(c);
		this.setTile(tile);
		this.setLayout(new GridLayout(0,3,0,0)); // gap = 0
		for(int i = 0; i < 9; i++) {
			this.add(new SquareView(this.getController(), this.getTile().nextSquare()));
		}
		this.setVisible(true);
	}
}
