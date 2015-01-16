package isom3320.project.game;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame GameFrame = new JFrame("Game Panel");
		GameFrame.add(new GamePanel());
		GameFrame.setMaximumSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		GameFrame.setMinimumSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		GameFrame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		GameFrame.setLocationRelativeTo(null);
		GameFrame.setResizable(false);
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.pack();
		GameFrame.setVisible(true);
	}
}
