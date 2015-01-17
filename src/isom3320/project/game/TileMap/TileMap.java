package isom3320.project.game.TileMap;

import isom3320.project.game.GamePanel;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class TileMap {
	// position
	private double xPosition;
	private double yPosition;

	// bounds
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;

	private double tween;

	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}

	public void loadTiles(String s) {

		try {

			tileset = Multimedia.getImageByName(s);
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void loadMap(String s) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(s));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xMin = GamePanel.WIDTH - width;
			xMax = 0;
			yMin = GamePanel.HEIGHT - height;
			yMax = 0;

			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getTileSize() { 
		return tileSize;
	}

	public double getXPosition() { 
		return xPosition; 
	}

	public double getYPosition() { 
		return yPosition; 
	}

	public int getWidth() { 
		return width; 
	}
	public int getHeight() { 
		return height; 
	}

	public int getTileType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}

	public void setTween(double d) { 
		tween = d;
	}

	public void setPosition(double x, double y) {

		this.xPosition += (x - this.xPosition) * tween;
		this.yPosition += (y - this.yPosition) * tween;

		fixBounds();

		colOffset = (int)-this.xPosition / tileSize;
		rowOffset = (int)-this.yPosition / tileSize;

	}

	private void fixBounds() {
		if(xPosition < xMin)
			xPosition = xMin;
		if(yPosition < yMin) 
			yPosition = yMin;
		if(xPosition > xMax) 
			xPosition = xMax;
		if(yPosition > yMax)
			yPosition = yMax;
	}

	public void draw(Graphics2D g2d) {
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
			if(row >= numRows) {
				break;
			}

			for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
				if(col >= numCols) {
					break;
				}
				if(map[row][col] == 0) {
					continue;
				}
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g2d.drawImage(tiles[r][c].getImage(), (int)xPosition + col * tileSize, (int)yPosition + row * tileSize, null);

			}

		}

	}

}