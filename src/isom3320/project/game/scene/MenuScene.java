package isom3320.project.game.scene;

import isom3320.project.game.GamePanel;
import isom3320.project.game.object.GameObject;
import isom3320.project.game.scene.SceneManager.SceneLevel;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuScene extends Scene {
	private int currentOption;
	private String[] menuOption;
	private BufferedImage bgImg;
	
	public MenuScene() {
		currentOption = 0;
		menuOption = new String[] {
				"Start",
				"Quit"
		};
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		bgImg = Multimedia.getImageByName("water.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		int imgWidth = bgImg.getWidth();
		int imgHeight = bgImg.getHeight();
		int numOfImgInRow = GamePanel.WIDTH / imgWidth;
		int numOfImgInCol = GamePanel.HEIGHT / imgHeight;
		
		for(int i = 0; i < numOfImgInCol; i++) {
			for(int j = 0; j < numOfImgInRow; j++) {
				g2d.drawImage(bgImg, j * imgWidth, i * imgHeight, imgWidth, imgHeight, null);
			}
		}
		
		for(GameObject gameObject : children) {
			gameObject.draw(g2d);
		}
		
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 36);
		g2d.setFont(font);
		g2d.drawString("MENU SCENE", GamePanel.WIDTH / 3, 36);
		
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		g2d.setFont(font);
		for(int i = 0; i < menuOption.length; i++) {
			if(i == currentOption) {
				g2d.setColor(Color.yellow);
			}
			else {
				g2d.setColor(Color.black);
			}
			
			g2d.drawString(menuOption[i], GamePanel.WIDTH / 2 - 11, GamePanel.HEIGHT / 2 + i * 20);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			currentOption--;
			if(currentOption < 0) {
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
				System.exit(0);
			}
			else if(currentOption == 0) {
				SceneManager.getInstance().changeScene(SceneLevel.LEVEL1);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
