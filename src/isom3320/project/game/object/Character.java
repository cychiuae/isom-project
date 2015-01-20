package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;

import java.awt.Graphics2D;

abstract public class Character extends GameObject {
	protected int hp;
	protected int maxHp;
	protected boolean flinching;
	protected long flinchTimer;
	
	protected boolean isDead;
	
	public Character(TileMap tm) {
		super(tm);
		// TODO Auto-generated constructor stub
	}

	public int getHp() { 
		return hp; 
	}
	public int getMaxHp() { 
		return maxHp; 
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if(facingRight) {
			g2d.drawImage(animation.getImage(), (int)(xPosition + xMapPosition - width / 2), (int)(yPosition + yMapPosition - height / 2), null);
		}
		else {
			g2d.drawImage(animation.getImage(), (int)(xPosition + xMapPosition - width / 2 + width), (int)(yPosition + yMapPosition - height / 2), -width, height, null);
		}
	}
}
