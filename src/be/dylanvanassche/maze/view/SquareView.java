// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import be.dylanvanassche.maze.controller.Controller;
import be.dylanvanassche.maze.model.*;

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
		this.add(new SquareViewElement(this.getSquare().getContent()));
		this.setVisible(true);
	}
}
