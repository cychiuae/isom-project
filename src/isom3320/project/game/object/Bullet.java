package isom3320.project.game.object;

import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bullet extends GameObject{
	private BufferedImage bulletImg;
	private int speed = 10;
	private boolean show;
	int scale;
	
	public Bullet(int xPos, int yPos) {
		super(xPos, yPos);
		bulletImg = Multimedia.getImageByName("bigBullet1.png");
		width = bulletImg.getWidth();
		height = bulletImg.getHeight();
		show = true;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(show) {
			g2d.drawImage(bulletImg, xPosition, yPosition, bulletImg.getHeight(), bulletImg.getWidth(), null);
		} 
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		yPosition -= speed;
		if(yPosition < 0) {
			show = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
