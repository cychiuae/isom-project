package isom3320.project.game.object;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

abstract public class GameObject {
	protected int xPosition;
	protected int yPosition;
	protected int width;
	protected int height;
	
	public GameObject() {
		xPosition = yPosition = 0;
	}
	
	public GameObject(int xPos, int yPos) {
		xPosition = xPos;
		yPosition = yPos;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	abstract public void draw(Graphics2D g2d);
	
	abstract public void update();
	
	abstract public void keyTyped(KeyEvent e);
	
	abstract public void keyReleased(KeyEvent e);
	
	abstract public void keyPressed(KeyEvent e);
}
