package isom3320.project.game.scene;

public class SceneManager {
	private static SceneManager instance;
	public static enum SceneLevel {
		MENU,
		LEVEL1
	};
	
	private Scene currentScene;
	private SceneLevel currentSceneLevel;
	
	public static SceneManager getInstance() {
		if(instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	private SceneManager() {
		currentSceneLevel = SceneLevel.LEVEL1;
		currentScene = new MenuScene();
	}
	
	public SceneLevel getCurrentSceneLevel() {
		return currentSceneLevel;
	}
	
	public Scene getCurrentScene() {
		return currentScene;
	}
	
	public void changeScene(SceneLevel sceneLevel) {
		currentSceneLevel = sceneLevel;
		if(currentSceneLevel == SceneLevel.LEVEL1) {
			currentScene = null;
			currentScene = new Scene1();
		}
	}
}
