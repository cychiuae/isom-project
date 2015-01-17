package isom3320.project.game.TileMap;

import java.awt.image.BufferedImage;

public class Tile {
	final public static int NORMAL = 0;
	final public static int BLOCK = 1;
	
	private BufferedImage image;
	private int type;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getType() {
		return type;
	}
}
