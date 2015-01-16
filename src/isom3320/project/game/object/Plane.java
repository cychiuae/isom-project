package isom3320.project.game.object;

import isom3320.project.game.GamePanel;
import isom3320.project.game.scene.Scene1;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Plane extends GameObject {
	private BufferedImage planeImage;
	private int speed = 5;
	private boolean up, down, left, right;
	
	public Plane(int xPos, int yPos) {
		super(xPos, yPos);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		planeImage = Multimedia.getImageByName("plane2_1.png");
		width = planeImage.getWidth();
		height = planeImage.getHeight();
		up = down = left = right = false;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.drawImage(planeImage, xPosition, yPosition, planeImage.getHeight(), planeImage.getWidth(), null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(up) {
			yPosition -= speed;
		}
		if(down) {
			yPosition += speed;
		}
		if(left) {
			xPosition -= speed; 
		}
		if(right) {
			xPosition += speed;
		}
		
		if(xPosition < 0) {
			xPosition = 0;
		}
		if(xPosition > GamePanel.WIDTH - width) {
			xPosition = GamePanel.WIDTH - width;
		}
		if(yPosition < 0) {
			yPosition = 0;
		}
		if(yPosition > GamePanel.HEIGHT - height) {
			yPosition = GamePanel.HEIGHT - height;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Scene1.bullet.add(new Bullet(xPosition + width / 2, yPosition + 2));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
	}

}
