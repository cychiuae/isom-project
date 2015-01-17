package isom3320.project.game;

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

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	final public static int WIDTH = 320;
	final public static int HEIGHT = 240;
	final public static int SCALE = 2;
	
	private SceneManager sceneManager;
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	private BufferedImage image;
	private Graphics2D g2d;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();	
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();
		running = true;
		sceneManager = SceneManager.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			if(wait < 0) 
				wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		sceneManager.update();
	}
	
	private void draw() {
		sceneManager.draw(g2d);
	}
	
	private void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		sceneManager.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		sceneManager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		sceneManager.keyReleased(e);
	}
	
}
