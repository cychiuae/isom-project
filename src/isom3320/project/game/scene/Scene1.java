package isom3320.project.game.scene;

import isom3320.project.game.GamePanel;
import isom3320.project.game.HUD;
import isom3320.project.game.TileMap.Background;
import isom3320.project.game.TileMap.TileMap;
import isom3320.project.game.object.Boss;
import isom3320.project.game.object.Dragon;
import isom3320.project.game.object.Enemy;
import isom3320.project.game.object.Explosion;
import isom3320.project.game.object.FireBall;
import isom3320.project.game.object.Slugger;
import isom3320.project.game.scene.SceneManager.SceneLevel;
import isom3320.project.game.score.Score;
import isom3320.project.game.score.ScoreSystem;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Scene1 extends Scene {

	private TileMap tileMap;
	private Background background;
	private HUD hud;
	private Dragon dragon;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;

	private Score score;
	private Font font;

	private int currentOption;
	private String[] menuOption;

	private boolean inited;
	private boolean stop;

	public Scene1() {
		inited = false;
		stop = false;
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

		populateEnemies();

		explosions = new ArrayList<Explosion>();

		score = new Score("Player", 0);
		hud = new HUD(dragon);
		hud.setScore(score);

		font = new Font("Arial", Font.PLAIN, 12);

		currentOption = 0;
		menuOption = new String[] {
				"Resume",
				"Restart",
				"Quit"
		};

		if(inited) {
			ScoreSystem.getInstance().addScoreRecord(score);
		}
		inited = true;
	}

	public void populateEnemies() {
		enemies = new ArrayList<Enemy>();
		Slugger s;
		Point[] points = new Point[] {
				new Point(200, 100),
				new Point(860, 200),
				new Point(1525, 200),
				new Point(1680, 200),
				new Point(1800, 200)
		};
		for(int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
		
		Boss b = new Boss(tileMap);
		b.setPosition(3000, 100);
		enemies.add(b);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(stop) {
			return;
		}

		if(dragon.isDead()) {
			SceneManager.getInstance().changeScene(SceneLevel.GAMEOVER);
		}
		
		dragon.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - dragon.getXPosition(), GamePanel.HEIGHT / 2 - dragon.getYPosition());
		background.setPosition(tileMap.getXPosition(), tileMap.getYPosition());

		dragon.checkAttack(enemies);

		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				score.addScore(1);
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getXPosition(), e.getYPosition()));
			}
		}
		
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			explosions.get(i).setMapPosition((int)tileMap.getXPosition(), (int)tileMap.getYPosition());
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		background.draw(g2d);
		tileMap.draw(g2d);
		dragon.draw(g2d);
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g2d);
		}
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g2d);
		}
		hud.draw(g2d);

		if(stop) {
			g2d.setColor(new Color(0.2f, 0.2f, 0.2f, 0.5f));
			g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			drawOptions(g2d);
		}
	}

	private void drawOptions(Graphics2D g2d) {
		g2d.setFont(font);

		for(int i = 0; i < menuOption.length; i++) {
			if(i == currentOption) {
				g2d.setColor(Color.RED);
			}
			else {
				g2d.setColor(Color.BLACK);
			}	
			g2d.drawString(menuOption[i], 145, 120 + i * 15);
		}
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

		switch(k.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				stop = !stop;
				if(stop) {
					currentOption = 0;
				}
				break;
			case KeyEvent.VK_UP:
				if(stop) {
					currentOption--;
					if(currentOption == -1) {
						currentOption = menuOption.length - 1;
					}
				}
				break;
			case KeyEvent.VK_DOWN:
				if(stop) {
					currentOption++;
					if(currentOption >= menuOption.length) {
						currentOption = 0;
					}
				}
				break;
				
			case KeyEvent.VK_ENTER:
				if(stop) {
					stop = !stop;
					if(currentOption == 1) {
						SceneManager.getInstance().changeScene(SceneLevel.LEVEL1);
					}
					if(currentOption == menuOption.length - 1) {
						SceneManager.getInstance().changeScene(SceneLevel.MENU);
					}
				}
				break;
		}
	}

}
