package isom3320.project.game.object;


import isom3320.project.game.GamePanel;
import isom3320.project.game.TileMap.Tile;
import isom3320.project.game.TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public abstract class GameObject {
	protected TileMap tileMap;
	protected int tileSize;
	protected double xMapPosition;
	protected double yMapPosition;

	protected double xPosition;
	protected double yPosition;
	protected double dx;
	protected double dy;

	protected int width;
	protected int height;

	protected int collisionWidth;
	protected int collisionHeight;

	protected int currentRow;
	protected int currentCol;
	protected double xDestination;
	protected double yDestination;
	protected double xTemp;
	protected double yTemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;

	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	// constructor
	public GameObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize(); 
	}

	public boolean intersects(GameObject o) {
		return this.getRectangle().intersects(o.getRectangle());
	}

	public Rectangle getRectangle() {
		return new Rectangle((int)xPosition - collisionWidth, (int)yPosition - collisionHeight, collisionWidth, collisionHeight);
	}

	public void calculateCorners(double x, double y) {

		int leftTile = (int)(x - collisionWidth / 2) / tileSize;
		int rightTile = (int)(x + collisionWidth / 2 - 1) / tileSize;
		int topTile = (int)(y - collisionHeight / 2) / tileSize;
		int bottomTile = (int)(y + collisionHeight / 2 - 1) / tileSize;

		int topLeftTileType = tileMap.getTileType(topTile, leftTile);
		int topRightTileType = tileMap.getTileType(topTile, rightTile);
		int bottomLeftTileType = tileMap.getTileType(bottomTile, leftTile);
		int bottomRightTileType = tileMap.getTileType(bottomTile, rightTile);

		topLeft = topLeftTileType == Tile.BLOCKED;
		topRight = topRightTileType == Tile.BLOCKED;
		bottomLeft = bottomLeftTileType == Tile.BLOCKED;
		bottomRight = bottomRightTileType == Tile.BLOCKED;
	}

	public void checkTileMapCollision() {
		//TODO: IF the hero fall out of frame, the program will crash
		//TODO: Fix it
		
		currentCol = (int)xPosition / tileSize;
		currentRow = (int)yPosition / tileSize;

		xDestination = xPosition + dx;
		yDestination = yPosition + dy;

		xTemp = xPosition;
		yTemp = yPosition;

		calculateCorners(xPosition, yDestination);

		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				yTemp = currentRow * tileSize + collisionHeight / 2;
			}
			else {
				yTemp += dy;
			}
		}

		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				yTemp = (currentRow + 1) * tileSize - collisionHeight / 2;
			}
			else {
				yTemp += dy;
			}
		}

		calculateCorners(xDestination, yPosition);

		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				xTemp = currentCol * tileSize + collisionWidth / 2;
			}
			else {
				xTemp += dx;
			}
		}

		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				xTemp = (currentCol + 1) * tileSize - collisionWidth / 2;
			}
			else {
				xTemp += dx;
			}
		}

		if(!falling) {
			calculateCorners(xPosition, yDestination + 1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
		}

	}

	public int getXPosition() { 
		return (int)xPosition; 
	}

	public int getYPosition() { 
		return (int)yPosition; 
	}

	public int getWidth() { 
		return width;
	}

	public int getHeight() { 
		return height;
	}

	public int getCollisionWidth() { 
		return collisionWidth; 
	}

	public int getCollisionHeight() { 
		return collisionHeight; 
	}

	public void setPosition(double xPos, double yPos) {
		this.xPosition = xPos;
		this.yPosition = yPos;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setMapPosition() {
		xMapPosition = tileMap.getXPosition();
		yMapPosition = tileMap.getYPosition();
	}

	public void setLeft(boolean b) { 
		left = b; 
	}
	
	public void setRight(boolean b) { 
		right = b; 
	}
	
	public void setUp(boolean b) { 
		up = b; 
	}
	
	public void setDown(boolean b) {
		down = b; 
	}
	
	public void setJumping(boolean b) {
		jumping = b;
	}

	public boolean isOnScreen() {
		return !(xPosition + xMapPosition + width < 0 ||
				 xPosition + xMapPosition - width > GamePanel.WIDTH ||
				 yPosition + yMapPosition + height < 0 ||
				 yPosition + yMapPosition - height > GamePanel.HEIGHT);
	}
	
	abstract public void update();
	
	abstract public void draw(Graphics2D g2d);
	
	abstract public void keyTyped(KeyEvent k);
	
	abstract public void keyPressed(KeyEvent k);
	
	abstract public void keyReleased(KeyEvent k);
}