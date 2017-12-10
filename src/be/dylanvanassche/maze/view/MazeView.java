// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import be.dylanvanassche.maze.controller.*;
import be.dylanvanassche.maze.model.*;

public class MazeView extends JPanel {
	private Controller controller;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public MazeView(final Controller c) {
		System.out.println("Creating Maze View");
		this.setController(c);
		this.setLayout(new GridLayout(0,4));
		this.newGame();
		this.setVisible(true);
	}
	
	public void newGame() {
		this.removeAll();
		for(int i = 0; i < 16; i++) {
			this.add(new TileView(this.getController(), this.getController().nextMazeTile()));
		}
	}
}
