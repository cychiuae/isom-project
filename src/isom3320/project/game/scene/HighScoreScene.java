package isom3320.project.game.scene;

import isom3320.project.game.TileMap.Background;
import isom3320.project.game.scene.SceneManager.SceneLevel;
import isom3320.project.game.score.Score;
import isom3320.project.game.score.ScoreSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class HighScoreScene extends Scene {
	private Background background;
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	private Score[] scores;

	public HighScoreScene() {
		background = new Background("menubg.gif", 1);
		background.setVector(-0.1, 0);
		
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 28);
		font = new Font("Arial", Font.PLAIN, 12);
		
		scores = ScoreSystem.getInstance().getTopThreeScore();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		scores = ScoreSystem.getInstance().getTopThreeScore();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		background.update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		background.draw(g2d);
		
		g2d.setColor(titleColor);
		g2d.setFont(titleFont);
		g2d.drawString("Highest Score", 70, 70);
		
		g2d.setFont(font);
		for(int i = 0; i < scores.length; i++) {
			g2d.drawString(i + 1 + ". " + scores[i].getPlayerName() + "....................................." + scores[i].getScore(), 80, 100 + 20 * i);
		}
		
		g2d.setColor(Color.RED);
		g2d.drawString("Back", 145, 200);
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
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				SceneManager.getInstance().changeScene(SceneLevel.MENU);
				break;
		}
	}

}
