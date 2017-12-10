// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import javax.swing.*;
import be.dylanvanassche.maze.controller.*;

public class MainFrame extends JFrame {
	private Controller controller;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public MainFrame(Controller c) {
		System.out.println("Creating Main Frame");
		this.setController(c);
		this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.newGame();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new MenuBar(this.getController()));
		this.pack();
		this.setVisible(true);
	}
	
	public void newGame() {
		this.getContentPane().removeAll(); // clean it up
		this.getContentPane().add(new MazeView(this.getController()));
		this.getContentPane().add(new NavigationView(this.getController()));
		// Revalidates the component hierarchy, when adding/removing stuff at runtime you need to reload the UI, 
		// this is NOT repaint since we add/remove the components completely without modifying their properties!
		// If you modify their properties only, a repaint() is sufficient!
		this.revalidate();
	}
	
	public void updateGame() {
		// Updating the location of the Player requires only a repainting of the components.
		this.repaint();
	}
}
