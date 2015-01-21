package isom3320.project.game.scene;

import isom3320.project.game.GamePanel;
import isom3320.project.game.scene.SceneManager.SceneLevel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class WinScene extends Scene {

	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public WinScene() {
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 25);
		font = new Font("Century Gothic", Font.PLAIN, 20);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
		g2d.setColor(titleColor);
		g2d.setFont(titleFont);
		g2d.drawString("OH! You seems lucky!", 40, 100);
		g2d.setFont(font);
		g2d.drawString("BACK", 135, 170);
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			SceneManager.getInstance().changeScene(SceneLevel.MENU);
		}
	}

}