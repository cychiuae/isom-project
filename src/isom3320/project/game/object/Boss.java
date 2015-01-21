package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Boss extends Enemy {
	private int fire;
	private int maxFire;

	private boolean soulBombing;
	private int soulBombingDamage;
	private FireBall soulBomb;

	private boolean bigBanging;
	private int bigBangDamage;
	private int bigBangRange;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames;

	private static final int WALKING = 0;
	private static final int SOULBOMB = 1;
	private static final int BIGBANG = 2;
	
	public long moveTimer;
	
	public Boss(TileMap tm) {
		super(tm);
		
		moveSpeed = 0.7;
		maxSpeed = 1.5;
		fallSpeed = 0.1;
		maxFallSpeed = 10.0;

		width = 40;
		height = 50;
		collisionWidth = 30;
		collisionHeight = 40;

		hp = maxHp = 5;
		damage = 3;
		fire = maxFire = 2500;

		soulBombingDamage = 5;
		soulBomb = new FireBall(tm, bigBanging);
		
		right = false;
		left = true;
		
		BufferedImage spritesheet = Multimedia.getImageByName("boss.gif");
		numFrames = new int[] {
			6, 6, 6
		};
		sprites = new ArrayList<BufferedImage[]>();
		for(int i = 0; i < 3; i++) {
			BufferedImage[] bi = new BufferedImage[numFrames[i]];

			for(int j = 0; j < numFrames[i]; j++) {
				bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
			}
			sprites.add(bi);
		}
		
		animation = new Animation();
		currentAction = WALKING;
		animation.setFrames(sprites.get(WALKING));
		animation.setDelay(400);
	}
	
	public int getFire() {
		return fire; 
	}
	public int getMaxFire() { 
		return maxFire;
	}

	public void setSoulBombing() { 
		soulBombing = true;
	}
	public void setBigBanging() {
		bigBanging = true;
	}
	
	private void getNextPosition() {
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				soulBomb();
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				soulBomb();
				dx = maxSpeed;
			}
		}

		if(falling) {
			dy += fallSpeed;
		}
	}
	
	private void soulBomb() {
		currentAction = SOULBOMB;
	}
	
	private void bigBang() {
		currentAction = BIGBANG;
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		currentAction = WALKING;
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);
		
		if(right && dx == 0) {
			facingRight = right = false;
			left = true;
			bigBang();
		}
		else if(left && dx == 0) {
			facingRight = right = true;
			left = false;
			bigBang();
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
