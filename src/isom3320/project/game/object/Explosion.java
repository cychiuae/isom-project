package isom3320.project.game.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import isom3320.project.game.utiliy.Multimedia;

public class Explosion {
	private int xPosition;
	private int yPosition;
	private int xMapPosition;
	private int yMapPosition;
	
	private int width;
	private int height;
	
	private Animation animation;
	private BufferedImage[] sprites;
	private boolean remove;
	
	public Explosion(int x, int y) {
		// TODO Auto-generated constructor stub
		xPosition = x;
		yPosition = y;
		
		width = height = 30;
		BufferedImage spritesheet = Multimedia.getImageByName("explosion.gif");
		sprites = new BufferedImage[6];
		for(int i = 0; i < sprites.length; i++) {
			sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(70);
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) {
			remove = true;
		}
	}

	public boolean shouldRemove() {
		return remove;
	}
	
	public void setMapPosition(int x, int y) {
		xMapPosition = x;
		yMapPosition = y;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(animation.getImage(), xPosition + xMapPosition - width / 2, yPosition + yMapPosition - height / 2, width, height, null);
	}
}
