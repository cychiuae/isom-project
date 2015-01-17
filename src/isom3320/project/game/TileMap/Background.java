package isom3320.project.game.TileMap;

import isom3320.project.game.GamePanel;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Background {
	private BufferedImage image;
	private double xPosition;
	private double yPosition;
	private double dx;
	private double dy;
	private double moveScale;

	public Background(String imageName, double moveScale) {
		image = Multimedia.getImageByName(imageName);
		this.moveScale = moveScale;
	}

	public void setPosition(double xPos, double yPos) {
		xPosition = (xPos * moveScale) % GamePanel.WIDTH;
		yPosition = (yPos * moveScale) % GamePanel.HEIGHT;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update() {
		xPosition += dx;
		yPosition += dy;

		xPosition = (xPosition * moveScale) % GamePanel.WIDTH;
		yPosition = (yPosition * moveScale) % GamePanel.HEIGHT;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int)xPosition, (int)yPosition, null);

		if(xPosition < 0) {
			g2d.drawImage(image, (int)xPosition + GamePanel.WIDTH, (int)yPosition, null);
		}
		if(xPosition > 0) {
			g2d.drawImage(image, (int)xPosition - GamePanel.WIDTH, (int)yPosition, null);
		}
	}
}
