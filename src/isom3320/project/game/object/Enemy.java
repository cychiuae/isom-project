package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

abstract public class Enemy extends Character {

	protected int hp;
	protected int maxHp;
	protected boolean isDead;
	protected int damage;
	
	protected boolean flinching;
	protected long flinchTimer;
	
	public Enemy(TileMap tm) {
		super(tm);
	}

	public boolean isDead() {
		return isDead;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void hit(int damage) {
		if(isDead || flinching) {
			return;
		}
		
		hp -= damage;
		if(hp < 0) {
			hp = 0;
		}
		if(hp == 0) {
			isDead = true;
		}
		
		flinching = true;
		flinchTimer = System.nanoTime();
	}
}
