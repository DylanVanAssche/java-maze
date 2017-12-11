// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import be.dylanvanassche.maze.controller.*;
import be.dylanvanassche.maze.model.*;

public class MazeView extends JPanel {
	public static final int mazeSize = 2; // define n
	private Controller controller;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public MazeView(final Controller c) {
		this.setController(c);
		this.setLayout(new GridLayout(0,4));
		this.newGame();
		this.setVisible(true);
	}
	
	public void newGame() {
		this.removeAll();
		for(int i = 0; i < Math.pow(2*mazeSize, 2); i++) {
			this.add(new TileView(this.getController(), this.getController().nextMazeTile()));
		}
	}
}
