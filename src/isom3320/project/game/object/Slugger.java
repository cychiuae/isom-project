package isom3320.project.game.object;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.utiliy.Multimedia;

public class Slugger extends Enemy {

	private BufferedImage[] sprites;

	public Slugger(TileMap tm) {
		super(tm);

		moveSpeed = 0.3;
		maxSpeed = 0.3;
		fallSpeed = 0.1;
		maxFallSpeed = 10.0;

		width = 30;
		height = 30;
		collisionHeight = 20;
		collisionWidth = 20;

		hp = maxHp = 2;
		damage = 1;

		BufferedImage spritesheet = Multimedia.getImageByName("slugger.gif");
		sprites = new BufferedImage[3];
		for(int i = 0; i < 3; i++) {
			sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
		} 
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);

		right = true;
	}

	private void getNextPosition() {
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}

		if(falling) {
			dy += fallSpeed;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);

		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}

		if(right && dx == 0) {
			facingRight = right = false;
			left = true;
		}
		else if(left && dx == 0) {
			facingRight = right = true;
			left = false;
		}

		animation.update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//if(isOnScreen()) {
		setMapPosition();
		super.draw(g2d);
		//}
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
