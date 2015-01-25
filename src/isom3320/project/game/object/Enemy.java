package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;

abstract public class Enemy extends Character {
	protected int damage;
	
	public Enemy(TileMap tm) {
		super(tm);
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
