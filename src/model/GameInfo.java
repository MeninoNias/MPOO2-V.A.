package model;

public class GameInfo {

	private int playerX;
	private int playerY;
	private int life;
	private int hearth;
	private int score;
	private int level;
	
	public GameInfo(int playerX, int playerY, int life, int hearth, int score, int level) {
		this.playerX = playerX;
		this.playerY = playerY;
		this.life = life;
		this.hearth = hearth;
		this.score = score;
		this.level = level;
	}
	
	
	public String toString() {
		return level+" "+playerX+" "+playerY+" "+life+" "+hearth+" "+score;
	}
	
	
}
