// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import javax.swing.*;
import be.dylanvanassche.maze.controller.*;

public class NavigationView extends JPanel {
	private Controller controller;
	private JButton upButton = new JButton("UP");
	private JButton downButton = new JButton("DOWN");
	private JButton leftButton = new JButton("LEFT");
	private JButton rightButton = new JButton("RIGHT");
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public JButton getUpButton() {
		return upButton;
	}

	public void setUpButton(JButton upButton) {
		this.upButton = upButton;
	}

	public JButton getDownButton() {
		return downButton;
	}

	public void setDownButton(JButton downButton) {
		this.downButton = downButton;
	}

	public JButton getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(JButton leftButton) {
		this.leftButton = leftButton;
	}

	public JButton getRightButton() {
		return rightButton;
	}

	public void setRightButton(JButton rightButton) {
		this.rightButton = rightButton;
	}

	public NavigationView(Controller c) {
		System.out.println("Creating NavigationView View");
		this.setController(c);
		this.setLayout(new BorderLayout());
		this.add(this.getUpButton(), BorderLayout.NORTH);
		this.add(this.getDownButton(), BorderLayout.SOUTH);
		this.add(this.getLeftButton(), BorderLayout.WEST);
		this.add(this.getRightButton(), BorderLayout.EAST);
		this.setVisible(true);
	}
}
