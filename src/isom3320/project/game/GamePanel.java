package isom3320.project.game;

import isom3320.project.game.object.Plane;
import isom3320.project.game.scene.Scene;
import isom3320.project.game.scene.SceneManager;
import isom3320.project.game.utiliy.Multimedia;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Date;

public class GamePanel extends Canvas implements Runnable {
	final public static int WIDTH = 640;
	final public static int HEIGHT = 480;
	final public static int FPS = 16;
	
	private SceneManager sceneManager;
	private Thread thread;
	
	public GamePanel() {
		setSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
		init();
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				sceneManager.getCurrentScene().keyTyped(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				sceneManager.getCurrentScene().keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				sceneManager.getCurrentScene().keyPressed(e);
			}
		});
		
		requestFocus();
		
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}
	
	private void init() {
		sceneManager = SceneManager.getInstance();
	}
	
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
		sceneManager.getCurrentScene().update();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		
		sceneManager.getCurrentScene().draw(g2d);
		
		g2d.dispose();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			this.repaint();
			try {
				thread.sleep(FPS);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
