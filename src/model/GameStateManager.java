package model;

import java.awt.Graphics2D;
import java.util.ArrayList;

import view.GamePanel;

public class GameStateManager {

	private GameState[] gameStates;
	private int atualState;
	
	private BaseDados dados;
	
	private boolean pause;
	private PauseState pauseState;
	
	public static final int NUMGAMESTATES = 5;
	public static final int AJUDASTATES = 4;
	public static final int CREDITOS = 3;
	public static final int MENUSTATE = 0;
	public static final int MISSAO = 1;
	public static final int LEVEL1STATE = 2;
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
	
		dados = new BaseDados();
		
		pause = false;
		pauseState = new PauseState(this);
		
		atualState = MENUSTATE;
		loadState(atualState);
		
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == MISSAO)
			gameStates[state] = new Missao01(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1(this);
		if(state == CREDITOS)
			gameStates[state] = new Credito(this);
		if(state == AJUDASTATES)
			gameStates[state] = new AjudaMenu(this);
		
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(atualState);
		atualState = state;
		loadState(atualState);
		gameStates[atualState].init();
	}
	

	public void update() {
		if(pause) {
			pauseState.update();
			return;
		}
		if(gameStates[atualState] != null) gameStates[atualState].update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		if(pause) {
			pauseState.draw(g);
			return;
		}
		if(gameStates[atualState] != null) gameStates[atualState].draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.LARGURA, GamePanel.ALTURA);
		}
	}

	public void setPause(boolean pause) {this.pause = pause;}

	public void keyPressed(int k) {
		if(pause) {
			pauseState.keyPressed(k);
			return;
		}
		gameStates[atualState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[atualState].keyReleased(k);
	}
	
}

