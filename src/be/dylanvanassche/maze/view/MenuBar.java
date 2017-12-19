package be.dylanvanassche.maze.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import be.dylanvanassche.maze.controller.*;

public class MenuBar extends JMenuBar {
	private Controller controller;
	private JMenu gameMenu = new JMenu("Game");
	private JMenu aboutMenu = new JMenu("About");
	private JMenu newGameMenu = new JMenu("New");
	private JMenuItem newGame2Menu = new JMenuItem("2x2");
	private JMenuItem newGame4Menu = new JMenuItem("4x4");
	private JMenuItem newGame8Menu = new JMenuItem("8x8");
	private JMenuItem exitGameMenu = new JMenuItem("Exit");
	private JMenuItem ApplicationAboutMenu = new JMenuItem("Application"); // Needs icon
	private JMenuItem GithubAboutMenu = new JMenuItem("GitHub"); // Needs icon
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public JMenu getGameMenu() {
		return gameMenu;
	}

	public void setGameMenu(JMenu game) {
		this.gameMenu = game;
	}
	
	public JMenu getAboutMenu() {
		return aboutMenu;
	}

	public void setAboutMenu(JMenu aboutMenu) {
		this.aboutMenu = aboutMenu;
	}

	public JMenu getNewGameMenu() {
		return newGameMenu;
	}

	public void setNewGameMenu(JMenu newGameMenu) {
		this.newGameMenu = newGameMenu;
	}
	
	public JMenuItem getNewGame2Menu() {
		return newGame2Menu;
	}

	public void setNewGame2Menu(JMenuItem newGame2Menu) {
		this.newGame2Menu = newGame2Menu;
	}

	public JMenuItem getNewGame4Menu() {
		return newGame4Menu;
	}

	public void setNewGame4Menu(JMenuItem newGame4Menu) {
		this.newGame4Menu = newGame4Menu;
	}

	public JMenuItem getNewGame8Menu() {
		return newGame8Menu;
	}

	public void setNewGame8Menu(JMenuItem newGame8Menu) {
		this.newGame8Menu = newGame8Menu;
	}

	public JMenuItem getExitGameMenu() {
		return exitGameMenu;
	}

	public void setExitGameMenu(JMenuItem exitGameMenu) {
		this.exitGameMenu = exitGameMenu;
	}

	public JMenuItem getApplicationAboutMenu() {
		return ApplicationAboutMenu;
	}

	public void setApplicationAboutMenu(JMenuItem applicationAboutMenu) {
		ApplicationAboutMenu = applicationAboutMenu;
	}

	public JMenuItem getGithubAboutMenu() {
		return GithubAboutMenu;
	}

	public void setGithubAboutMenu(JMenuItem githubAboutMenu) {
		GithubAboutMenu = githubAboutMenu;
	}

	public MenuBar(final Controller controller) {
		this.setController(controller);
		this.add(this.getGameMenu());
		this.add(Box.createHorizontalGlue());
		this.add(getAboutMenu());
		this.getGameMenu().add(this.getNewGameMenu());
		this.getNewGameMenu().add(this.getNewGame2Menu());
		this.getNewGameMenu().add(this.getNewGame4Menu());
		this.getNewGameMenu().add(this.getNewGame8Menu());
		this.getGameMenu().add(this.getExitGameMenu());
		this.getAboutMenu().add(this.getApplicationAboutMenu());
		this.getAboutMenu().add(this.getGithubAboutMenu());
		this.getNewGameMenu().setToolTipText("Start a new game");
		this.getNewGame2Menu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getController().setMazeSize(2);
				getController().newGame();
			}
		});
		this.getNewGame4Menu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getController().setMazeSize(4);
				getController().newGame();
			}
		});
		this.getNewGame8Menu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getController().setMazeSize(8);
				getController().newGame();
			}
		});
		this.getExitGameMenu().setToolTipText("Exit game");
		this.getExitGameMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getController().exitApplication();
			}
		});
		this.getApplicationAboutMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String dialogText = getController().getName() + "\nVersion: " + getController().getVersion() + "\nAuthor: " + getController().getAuthor();
				JOptionPane.showMessageDialog(null, dialogText, "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.getGithubAboutMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.github.com/dylanvanassche/"));
				} 
				catch(IOException e) {
					JOptionPane.showMessageDialog(null, "URI handler not found!\nDid you install a webbrowser?", "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(URISyntaxException e) {
					JOptionPane.showMessageDialog(null, "URI isn't conform with the RFC 2396!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.setVisible(true);
	}
}
