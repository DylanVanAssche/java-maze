// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import be.dylanvanassche.maze.controller.*;
import be.dylanvanassche.maze.model.BadMovementDirection;
import be.dylanvanassche.maze.model.MovementType;
import be.dylanvanassche.maze.model.UnknownMovementDirection;
import be.dylanvanassche.maze.model.WeHaveAWinner;

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

	public NavigationView(final Controller c) {
		this.setController(c);
		this.setLayout(new BorderLayout());
		this.add(this.getUpButton(), BorderLayout.NORTH);
		this.add(this.getDownButton(), BorderLayout.SOUTH);
		this.add(this.getLeftButton(), BorderLayout.WEST);
		this.add(this.getRightButton(), BorderLayout.EAST);
		this.getUpButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					getController().movePlayer(MovementType.UP);
				} 
				catch (UnknownMovementDirection exception) {
					showUnknownMovementDirectionDialog(exception.getMessage());
				}
				catch (BadMovementDirection exception) {
					showBadMovementDirectionDialog(exception.getMessage());
				}
				catch (WeHaveAWinner exception) {
					showWeHaveAWinnerDialog(exception.getMessage());
				}
			}
		});
		this.getDownButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					getController().movePlayer(MovementType.DOWN);
				} 
				catch (UnknownMovementDirection exception) {
					showUnknownMovementDirectionDialog(exception.getMessage());
				}
				catch (BadMovementDirection exception) {
					showBadMovementDirectionDialog(exception.getMessage());
				}
				catch (WeHaveAWinner exception) {
					showWeHaveAWinnerDialog(exception.getMessage());
				}
			}
		});
		this.getLeftButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					getController().movePlayer(MovementType.LEFT);
				} 
				catch (UnknownMovementDirection exception) {
					showUnknownMovementDirectionDialog(exception.getMessage());
				}
				catch (BadMovementDirection exception) {
					showBadMovementDirectionDialog(exception.getMessage());
				}
				catch (WeHaveAWinner exception) {
					showWeHaveAWinnerDialog(exception.getMessage());
				}
			}
		});
		this.getRightButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					getController().movePlayer(MovementType.RIGHT);
				} 
				catch (UnknownMovementDirection exception) {
					showUnknownMovementDirectionDialog(exception.getMessage());
				}
				catch (BadMovementDirection exception) {
					showBadMovementDirectionDialog(exception.getMessage());
				}
				catch (WeHaveAWinner exception) {
					showWeHaveAWinnerDialog(exception.getMessage());
				}
			}
		});
		this.setVisible(true);
	}
	
	public void showUnknownMovementDirectionDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void showBadMovementDirectionDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void showWeHaveAWinnerDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}
