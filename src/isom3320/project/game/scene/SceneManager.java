package isom3320.project.game.scene;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SceneManager {
	private static SceneManager instance;
	public static enum SceneLevel {
		MENU(0),
		LEVEL1(1),
		HIGHESTSCORE(2);
		
		private int level;
		
		private SceneLevel(int level) {
			this.level = level;
		}
		
		public int value() {
			return level;
		}
	};
	
	private ArrayList<Scene> scenes;
	private SceneLevel currentSceneLevel;
	
	public static SceneManager getInstance() {
		if(instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() {
		scenes = new ArrayList<Scene>();
		currentSceneLevel = SceneLevel.MENU;
		scenes.add(new MenuScene());
		scenes.add(new Scene1());
		scenes.add(new HighScoreScene());
	}
	
	public SceneLevel getCurrentSceneLevel() {
		return currentSceneLevel;
	}
	
	public Scene getCurrentScene() {
		return scenes.get(currentSceneLevel.value());
	}
	
	public void changeScene(SceneLevel sceneLevel) {
		currentSceneLevel = sceneLevel;
		scenes.get(currentSceneLevel.value()).init();
	}
	
	public void update() {
		scenes.get(currentSceneLevel.value()).update();
	}
	
	public void draw(Graphics2D g2d) {
		scenes.get(currentSceneLevel.value()).draw(g2d);
	}
	
	public void keyTyped(KeyEvent e) {
		scenes.get(currentSceneLevel.value()).keyTyped(e);
	}

	public void keyPressed(KeyEvent e) {
		scenes.get(currentSceneLevel.value()).keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		scenes.get(currentSceneLevel.value()).keyReleased(e);
	}
}
