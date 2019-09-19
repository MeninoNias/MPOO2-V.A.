package view;


import javax.swing.JFrame;

import model.GameStateManager;

public class Game extends JFrame{
	
	private GamePanel gamePanel;
	
	public Game() {
		super("MPOO");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		gamePanel = new GamePanel();
		setContentPane(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}
}
