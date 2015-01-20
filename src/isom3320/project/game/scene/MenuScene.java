package isom3320.project.game.scene;

import isom3320.project.game.TileMap.Background;
import isom3320.project.game.scene.SceneManager.SceneLevel;
import isom3320.project.game.score.ScoreSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene {
	private Background background;
	
	private int currentOption;
	private String[] menuOption;
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuScene() {
		currentOption = 0;
		menuOption = new String[] {
				"Start",
				"High Score",
				"Help",
				"Quit"
		};
		
		try {
			background = new Background("menubg.gif", 1);
			background.setVector(-0.1, 0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 28);
		font = new Font("Arial", Font.PLAIN, 12);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
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
		g2d.drawString("HIHIHI GAME", 80, 70);
		
		drawOptions(g2d);
	}

	public void drawOptions(Graphics2D g2d) {
		g2d.setFont(font);
		
		for(int i = 0; i < menuOption.length; i++) {
			if(i == currentOption) {
				g2d.setColor(Color.RED);
			}
			else {
				g2d.setColor(Color.BLACK);
			}
			if( i == 1) {
				g2d.drawString(menuOption[i], 130, 140 + i * 15);
			}
			else {
				g2d.drawString(menuOption[i], 145, 140 + i * 15);
			}	
		}		
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
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			currentOption--;
			if(currentOption == -1) {
				currentOption = menuOption.length - 1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentOption++;
			if(currentOption >= menuOption.length) {
				currentOption = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentOption == menuOption.length - 1) {
				ScoreSystem.getInstance().saveScoreFile();
				System.exit(0);
			}
			else if(currentOption == 0) {
				SceneManager.getInstance().changeScene(SceneLevel.LEVEL1);
			}
			else if(currentOption == 1) {
				SceneManager.getInstance().changeScene(SceneLevel.HIGHESTSCORE);
			}
			else if(currentOption == 2) {
				
			}
		}
		
	}

}
