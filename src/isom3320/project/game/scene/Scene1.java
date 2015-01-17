package isom3320.project.game.scene;

import isom3320.project.game.GamePanel;
import isom3320.project.game.TileMap.Background;
import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.object.Dragon;
import isom3320.project.game.object.FireBall;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Scene1 extends Scene {

	private TileMap tileMap;
	private Background background;
	private Dragon dragon;

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

		background = new Background("grassbg1.gif", 0.1);
		dragon = new Dragon(tileMap);
		dragon.setPosition(100, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		dragon.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - dragon.getXPosition(), GamePanel.HEIGHT / 2 - dragon.getYPosition());
		background.setPosition(tileMap.getXPosition(), tileMap.getYPosition());
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		background.draw(g2d);
		tileMap.draw(g2d);
		dragon.draw(g2d);
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		dragon.keyTyped(k);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		dragon.keyReleased(k);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		dragon.keyPressed(k);
	}

}
