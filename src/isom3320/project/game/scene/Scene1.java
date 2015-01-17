package isom3320.project.game.scene;

import isom3320.project.game.GamePanel;
import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.object.GameObject;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Scene1 extends Scene {

	private TileMap tileMap;
	
	public Scene1() {
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		tileMap = new TileMap(30);
		tileMap.loadTiles("grasstileset.gif");
		tileMap.loadMap("Resources/level1-1.txt");
		tileMap.setPosition(0, 0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//Clear screen
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
		
		tileMap.draw(g2d);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
