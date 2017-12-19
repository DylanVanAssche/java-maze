// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
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
		this.setController(c);
		this.setLayout(new GridLayout(0, 2*this.getController().getMazeSize(), 0, 0)); // gap = 0
		this.newGame();
		this.setVisible(true);
	}
	
	public void newGame() {
		this.removeAll();
		for(int i = 0; i < Math.pow(2*this.getController().getMazeSize(), 2); i++) {
			this.add(new TileView(this.getController(), this.getController().nextMazeTile()));
		}
	}
}
