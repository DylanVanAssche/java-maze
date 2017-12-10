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
	private JMenuItem newGameMenu = new JMenuItem("New");
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

	public JMenuItem getNewGameMenu() {
		return newGameMenu;
	}

	public void setNewGameMenu(JMenuItem newGameMenu) {
		this.newGameMenu = newGameMenu;
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
		this.getGameMenu().add(this.getExitGameMenu());
		this.getAboutMenu().add(this.getApplicationAboutMenu());
		this.getAboutMenu().add(this.getGithubAboutMenu());
		this.getNewGameMenu().setToolTipText("Start a new game");
		this.getNewGameMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//getController().newGame();
				//getView().newGame();
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
	}
}
