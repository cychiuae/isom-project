package isom3320.project.game.score;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreSystem {
	private static ScoreSystem instance;
	
	private ArrayList<Score> scores;
	
	public static ScoreSystem getInstance() {
		if(instance == null) {
			instance = new ScoreSystem();
		}
		
		return instance;
	}
	
	private ScoreSystem() {
		scores = new ArrayList<Score>();
		readScoreFile();
	}
	
	private void readScoreFile() {
		
	}
	
	public void saveScoreFile() {
		for(Score s : scores) {
			
		}
	}
	
	public void addScoreRecord(Score s) {
		scores.add(s);
	}
	
	private void sortScores() {
		scores.sort(new Comparator<Score>() {
			@Override
			public int compare(Score o1, Score o2) {
				// TODO Auto-generated method stub
				return o1.getScore() - o2.getScore();
			}
		});
	}
	
	public Score[] getTopThreeScore() {
		ArrayList<Score> result = new ArrayList<Score>();
		sortScores();
		
		for(int i = 0; i < scores.size() || i < 3; i++) {
			result.add(scores.get(i));
		}
		
		return result.toArray(new Score[result.size()]);
		
	}
}
