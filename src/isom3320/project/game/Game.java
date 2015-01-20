package isom3320.project.game;

import isom3320.project.game.score.ScoreSystem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame GameFrame = new JFrame("Game Panel");
		GameFrame.setContentPane(new GamePanel());
		GameFrame.setResizable(false);
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.pack();
		GameFrame.setVisible(true);
		GameFrame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				ScoreSystem.getInstance().saveScoreFile();
			}
			
		});
	}
}
