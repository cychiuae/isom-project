package isom3320.project.game;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame GameFrame = new JFrame("Game Panel");
		GameFrame.setContentPane(new GamePanel());
		GameFrame.setLocationRelativeTo(null);
		GameFrame.setResizable(false);
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.pack();
		GameFrame.setVisible(true);
	}
}
