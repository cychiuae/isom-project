package isom3320.project.game.object;
 
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.utiliy.Multimedia;

public class FireBall extends GameObject {
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public FireBall(TileMap tm, boolean right) {
		super(tm);
		
		moveSpeed = 3.8;
		if(right) {
			dx = moveSpeed;
		}
		else {
			dx = -moveSpeed;
		}
		
		width = height = 30;
		collisionHeight = 14;
		collisionWidth = 14;
		
		BufferedImage spritesheet = Multimedia.getImageByName("fireball.gif");
		
		sprites = new BufferedImage[4];
		for(int i = 0; i < sprites.length; i++) {
			sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
		}
		
		hitSprites = new BufferedImage[4];
		for(int i = 0; i < hitSprites.length; i++) {
			hitSprites[i] = spritesheet.getSubimage(i * width, height, width, height);
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(70);
	}
	
	public void setHit() {
		if(!hit) {
			hit= true;
			animation.setFrames(hitSprites);
			animation.setDelay(70);
			dx = 0;
		}
	}
	
	public boolean shoudlRemove() {
		return remove;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		checkTileMapCollision();
		setPosition(xTemp, yTemp);
		
		if(dx == 0 && !hit) {
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		setMapPosition();
		
		if(facingRight) {
			g2d.drawImage(animation.getImage(), (int)(xPosition + xMapPosition - width / 2), (int)(yPosition + yMapPosition - height / 2), null);
		}
		else {
			g2d.drawImage(animation.getImage(), (int)(xPosition + xMapPosition - width / 2 + width), (int)(yPosition + yMapPosition - height / 2), -width, height, null);
		}
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
