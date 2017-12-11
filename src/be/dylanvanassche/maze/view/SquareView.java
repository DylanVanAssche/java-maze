// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import be.dylanvanassche.maze.controller.*;
import be.dylanvanassche.maze.model.Square;
import be.dylanvanassche.maze.view.*;

public class SquareView extends JPanel {
private Controller controller;
private Square square;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public SquareView(final Controller c, Square s) {
		this.setController(c);
		this.setSquare(s);
		this.setLayout(new FlowLayout());
		this.add(new JLabel(this.getSquare().toString()));
		this.setVisible(true);
	}
}
