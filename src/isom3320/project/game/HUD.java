package isom3320.project.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import isom3320.project.game.object.Dragon;
import isom3320.project.game.utiliy.Multimedia;

public class HUD {
	private Dragon dragon;
	private BufferedImage image;
	private Font font;
	
	public HUD(Dragon d) {
		dragon = d;
		image = Multimedia.getImageByName("hud.gif");
		font = new Font("Arial", Font.PLAIN, 14);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, 0, 10, null);
		g2d.setFont(font);
		g2d.setColor(Color.white);
		g2d.drawString(dragon.getHp() + "/" + dragon.getMaxHp(), 30, 25);
		g2d.drawString(dragon.getFire() / 100 + "/" + dragon.getMaxFire() / 100, 30, 45);
	}
}
