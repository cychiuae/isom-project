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
		
		moveSpeed = 0.7;
		maxSpeed = 4;
		fallSpeed = 0.01;
		maxFallSpeed = 10.0;

		width = 60;
		height = 60;
		collisionHeight = 46;
		collisionWidth = 58;

		hp = maxHp = 5;
		damage = 3;
		
		right = false;
		left = true;
		
		BufferedImage sprites = Multimedia.getImageByName("plane2_1.png");
		
		BufferedImage[] s = new BufferedImage[1];
		s[0] = sprites;
		animation = new Animation();
		animation.setFrames(s);
		animation.setDelay(400);
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
