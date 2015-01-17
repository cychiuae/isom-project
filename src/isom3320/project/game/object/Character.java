package isom3320.project.game.object;

import isom3320.project.game.TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

abstract public class Character extends GameObject {

	public Character(TileMap tm) {
		super(tm);
		// TODO Auto-generated constructor stub
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
