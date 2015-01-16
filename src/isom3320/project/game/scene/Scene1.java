package isom3320.project.game.scene;

import isom3320.project.game.GamePanel;
import isom3320.project.game.object.GameObject;
import isom3320.project.game.object.Plane;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Scene1 extends Scene {
	
	private int currentOption;
	private String[] menuOption;
	private Plane p;
	private BufferedImage bgImg;
	private int move;
	private int speed;
	
	public Scene1() {
		currentOption = 0;
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		move = 0;
		speed = 1;
		
		p = new Plane(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		children.add(p);
		
		bgImg = Multimedia.getImageByName("water.png");
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		for(GameObject gameObject : children) {
			gameObject.update();
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		int imgWidth = bgImg.getWidth();
		int imgHeight = bgImg.getHeight();
		int numOfImgInRow = GamePanel.WIDTH / imgWidth;
		int numOfImgInCol = GamePanel.HEIGHT / imgHeight;
		
		for(int i = -1; i < numOfImgInCol; i++) {
			for(int j = 0; j < numOfImgInRow; j++) {
				g2d.drawImage(bgImg, j * imgWidth, i * imgHeight + ( move % imgHeight), imgWidth, imgHeight, null);
			}
		}
		
		move += speed;
		
		for(GameObject gameObject : children) {
			gameObject.draw(g2d);
		}
		
		g2d.drawString("Scene", GamePanel.WIDTH / 2, 10);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		for(GameObject gameObject : children) {
			gameObject.keyReleased(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		for(GameObject gameObject : children) {
			gameObject.keyPressed(e);
		}
	}

}
