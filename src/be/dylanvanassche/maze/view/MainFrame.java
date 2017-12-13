// Dylan Van Assche - 3 ABA EI
package be.dylanvanassche.maze.view;

import java.awt.BorderLayout;

import javax.swing.*;
import be.dylanvanassche.maze.controller.*;

public class MainFrame extends JFrame {
	private Controller controller;
	private MazeView mazeView;
	private NavigationView navigationView;
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public MazeView getMazeView() {
		return mazeView;
	}

	public void setMazeView(MazeView mazeView) {
		this.mazeView = mazeView;
	}

	public NavigationView getNavigationView() {
		return navigationView;
	}

	public void setNavigationView(NavigationView navigationView) {
		this.navigationView = navigationView;
	}

	public MainFrame(final Controller c) {
		this.setController(c);
		this.getContentPane().setLayout(new BorderLayout());
		this.setNavigationView(new NavigationView(this.getController()));
		this.newGame();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setJMenuBar(new MenuBar(this.getController()));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void newGame() {
		String name = null;
		while(name == null || name.length() == 0) {
			name = JOptionPane.showInputDialog(this, "What's your name?");
		}
		this.getController().setPlayerName(name);
		this.getContentPane().removeAll(); // clean it up
		this.setMazeView(new MazeView(this.getController()));
		this.getContentPane().add(this.getMazeView(), BorderLayout.CENTER);
		this.getContentPane().add(this.getNavigationView(), BorderLayout.SOUTH);
		// Revalidates the component hierarchy, when adding/removing stuff at runtime you need to reload the UI, 
		// this is NOT repaint since we add/remove the components completely without modifying their properties!
		// If you modify their properties only, a repaint() is sufficient!
		this.revalidate();
	}
	
	public void updateGame() {
		// Updating the location of the Player requires only a repainting of the components.
		this.getMazeView().newGame();
		this.revalidate();
		this.repaint();
	}
}
