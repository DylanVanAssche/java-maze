// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import be.dylanvanassche.maze.controller.*;

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
		for(int i = 0; i < 16; i++) {
			this.add(new TileView(this.getController(), this.getController().nextMazeTile()));
		}
		this.setVisible(true);
	}
}
