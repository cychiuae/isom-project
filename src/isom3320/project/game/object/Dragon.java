package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Dragon extends GameObject {
	private int hp;
	private int maxHp;
	private int fire;
	private int maxFire;
	private boolean isDead;
	private boolean flinching;
	private long flinchTimer;

	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	//private ArrayList<FireBall> fireBalls;

	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;

	private boolean gliding;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			2, 8, 1, 2, 4, 2, 5
	};

	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;

	public Dragon(TileMap tm) {
		super(tm);

		width = 30;
		height = 30;
		collisionWidth = 20;
		collisionHeight = 20;

		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;

		facingRight = true;

		hp = maxHp = 5;
		fire = maxFire = 2500;

		fireCost = 200;
		fireBallDamage = 5;
		//fireBalls = new ArrayList<FireBall>();

		scratchDamage = 8;
		scratchRange = 40;

		BufferedImage spritesheet = Multimedia.getImageByName("playersprites.gif");

		sprites = new ArrayList<BufferedImage[]>();
		for(int i = 0; i < 7; i++) {
			BufferedImage[] bi = new BufferedImage[numFrames[i]];

			for(int j = 0; j < numFrames[i]; j++) {
				if(i != SCRATCHING) {
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				}
				else {
					bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
				}
			}
			sprites.add(bi);
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
	}

	public int getHp() { 
		return hp; 
	}
	public int getMaxHp() { 
		return maxHp; }
	public int getFire() {
		return fire; 
	}
	public int getMaxFire() { 
		return maxFire;
	}

	public void setFiring() { 
		firing = true;
	}
	public void setScratching() {
		scratching = true;
	}
	public void setGliding(boolean b) { 
		gliding = b;
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
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}

		if((currentAction == SCRATCHING || currentAction == FIREBALL) && !(jumping || falling)) {
			dx = 0;
		}

		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}

		if(falling) {
			if(dy > 0 && gliding) {
				dy += fallSpeed * 0.1;
			}
			else {
				dy += fallSpeed;
			}

			if(dy > 0) {
				jumping = false;
			}
			if(dy < 0 && !jumping) {
				dy += stopJumpSpeed;
			}

			if(dy > maxFallSpeed) {
				dy = maxFallSpeed;
			}
		}
	}

	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xTemp, yTemp);

		if(currentAction == SCRATCHING) {
			if(animation.hasPlayedOnce()) {
				scratching = false;
			}

		}
		if(currentAction == FIREBALL) {
			if(animation.hasPlayedOnce()) {
				firing = false;
			}
		}

		if(flinching) {
			long elapsed =
					(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
			}
		}

		if(scratching) {
			if(currentAction != SCRATCHING) {
				//sfx.get("scratch").play();
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(50);
				width = 60;
			}
		}
		else if(firing) {
			if(currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy > 0) {
			if(gliding) {
				if(currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 30;
				}
			}
			else if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}

		animation.update();

		if(currentAction != SCRATCHING && currentAction != FIREBALL) {
			if(right) { 
				facingRight = true;
			}
			if(left) {
				facingRight = false;
			}
		}

	}

	public void draw(Graphics2D g) {
		setMapPosition();

		if(flinching) {
			long elapsed =
					(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}

		if(facingRight) {
			g.drawImage(animation.getImage(), (int)(xPosition + xMapPosition - width / 2), (int)(yPosition + yMapPosition - height / 2), null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(xPosition + xMapPosition - width / 2 + width), (int)(yPosition + yMapPosition - height / 2), -width, height, null);
		}
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_W) {
			jumping = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_R){
			scratching = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_E) {
			gliding = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_W) {
			jumping = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_R){
			scratching = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_E) {
			gliding = false;
		}
	}

}