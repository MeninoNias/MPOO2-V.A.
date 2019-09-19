package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Game;

public class Controller  implements KeyListener{

	private Game game;
	
	public Controller() {
		this.game = new Game();

		control();
	}

	private void control() {
		game.getGamePanel().addKeyListener(this);
	}

	public void keyPressed(KeyEvent k) {
		game.getGamePanel().getGms().keyPressed(k.getKeyCode());
	}
	public void keyReleased(KeyEvent k) {
		game.getGamePanel().getGms().keyReleased(k.getKeyCode());
	}
	public void keyTyped(KeyEvent e) {}

}