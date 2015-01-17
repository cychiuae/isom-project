package isom3320.project.game.TileMap;

import isom3320.project.game.GamePanel;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TileMap {
	private double xPosition;
	private double yPosition;
	//Bounds
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;
	//smooth moving camera
	private double tween;
	
	//map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	//tileset
	private BufferedImage tileSet;
	private int numTilesAcross;
	private Tile[][] tiles;
	
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
	
	public void loadTiles(String imageName) {
		tileSet = Multimedia.getImageByName(imageName);
		numTilesAcross = tileSet.getWidth() / tileSize;
		tiles = new Tile[2][numTilesAcross];
		
		BufferedImage subImage;
		for(int col = 0; col < numTilesAcross; col++) {
			subImage = tileSet.getSubimage(col * tileSize, 0, tileSize, tileSize);
			tiles[0][col] = new Tile(subImage, Tile.NORMAL);
			subImage = tileSet.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
			tiles[1][col] = new Tile(subImage, Tile.BLOCK);
		}
	}
	
	public void loadMap(String mapLocation) {
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(mapLocation));
			numCols = Integer.parseInt(fileReader.readLine());
			numRows = Integer.parseInt(fileReader.readLine());
			
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			String regex = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = fileReader.readLine();
				String[] tokens = line.split(regex);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getTileSize() {
		return tileSize;
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
	
	public int getTileType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles[r][c].getType();
	}
	
	public void setPosition(double xPos, double yPos) {
		xPosition += (xPos - xPosition) * tween;
		yPosition += (yPos - yPosition) * tween;
		fixBounds();
		
		colOffset = (int)-xPosition / tileSize;
		rowOffset = (int)-yPosition / tileSize;
	}
	
	private void fixBounds() {
		if(xPosition < xMin) {
			xPosition = xMin;
		}
		if(xPosition > xMax) {
			xPosition = xMax;
		}
		if(yPosition < yMin) {
			yPosition = yMin;
		}
		if(yPosition > yMax) {
			yPosition = yMax;
		}
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
