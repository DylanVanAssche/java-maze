package be.dylanvanassche.maze.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import be.dylanvanassche.maze.model.SquareType;


public class SquareViewElement extends JComponent {
	private SquareType content;
	private static final String WALL_PATH = "src/images/wall.png";
	private static final String PLAYER_PATH = "src/images/player.png";
	private static final String GOLD_PATH = "src/images/gold.png";
	private static final String FREE_PATH = "src/images/free.png";
	private static final int IMG_SIZE = 64;
	private BufferedImage wallImg;
	private BufferedImage playerImg;
	private BufferedImage goldImg;
	private BufferedImage freeImg;
	
	public SquareType getContent() {
		return content;
	}

	public void setContent(SquareType content) {
		this.content = content;
	}

	public BufferedImage getWallImg() {
		return wallImg;
	}

	public void setWallImg(BufferedImage wallImg) {
		this.wallImg = wallImg;
	}

	public BufferedImage getPlayerImg() {
		return playerImg;
	}

	public void setPlayerImg(BufferedImage playerImg) {
		this.playerImg = playerImg;
	}

	public BufferedImage getGoldImg() {
		return goldImg;
	}

	public void setGoldImg(BufferedImage goldImg) {
		this.goldImg = goldImg;
	}

	public BufferedImage getFreeImg() {
		return freeImg;
	}

	public void setFreeImg(BufferedImage freeImg) {
		this.freeImg = freeImg;
	}

	public SquareViewElement(SquareType content) {
		this.setContent(content);
		try {
			this.setWallImg(ImageIO.read(new File(WALL_PATH)));
			this.setPlayerImg(ImageIO.read(new File(PLAYER_PATH)));
			this.setGoldImg(ImageIO.read(new File(GOLD_PATH)));
			this.setFreeImg(ImageIO.read(new File(FREE_PATH)));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(IMG_SIZE,IMG_SIZE);
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics); 
		switch(this.getContent())
		{
		case WALL:
			graphics.drawImage(this.getWallImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			break;
		case PLAYER:
			graphics.drawImage(this.getPlayerImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			break;
		case GOLD:
			graphics.drawImage(this.getGoldImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			break;
		default:
		case FREE:
			graphics.drawImage(this.getFreeImg(), 0, 0, IMG_SIZE, IMG_SIZE, null);
			break;
		}

	}
}
