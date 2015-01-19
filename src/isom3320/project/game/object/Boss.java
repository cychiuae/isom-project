package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boss extends Enemy {

	public long moveTimer;
	
	public Boss(TileMap tm) {
		super(tm);
		
		hp = maxHp = 500;
		right = false;
		
		BufferedImage sprites = Multimedia.getImageByName("plane2_1.png");
		collisionWidth = width = sprites.getWidth();
		collisionWidth = height = sprites.getHeight();
		BufferedImage[] s = new BufferedImage[1];
		s[0] = sprites;
		animation = new Animation();
		animation.setFrames(s);
		animation.setDelay(400);
	}
	
	private void getNextPosition() {
		xTemp = 200;
		long e = (System.nanoTime() - moveTimer) / 100000;
		if(e > 400) {
			yTemp -= new Random().nextInt(1) - 14;
			moveTimer = System.nanoTime();
		}	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		getNextPosition();
		//checkTileMapCollision();
		setPosition(xTemp, yTemp);
		animation.update();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		setMapPosition();
		super.draw(g2d);
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub

	}

}
