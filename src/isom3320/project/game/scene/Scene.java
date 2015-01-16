package isom3320.project.game.scene;

import isom3320.project.game.object.GameObject;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

abstract public class Scene {
	
	protected ArrayList<GameObject> children;
	
	public Scene() {
		children = new ArrayList<GameObject>();
	}
	
	abstract public void init();
	
	abstract public void update();
	
	abstract public void draw(Graphics2D g2d);
	
	abstract public void keyTyped(KeyEvent e);
	
	abstract public void keyReleased(KeyEvent e);
	
	abstract public void keyPressed(KeyEvent e);
}
