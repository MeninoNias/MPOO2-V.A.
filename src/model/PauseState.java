package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import view.GamePanel;

public class PauseState extends GameState{

	private Font font;

	public PauseState(GameStateManager gsm) {
		this.gsm = gsm;
		font = new Font("Century Gothic", Font.PLAIN, 14);

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.LARGURA, GamePanel.ALTURA);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("GAME PAUSED", 110, 50);
		g.drawString("ESPAÇO PARA VOLTAR AO JOGO", 50, 130);
		g.drawString("ENTER PARA VOLTAR MENU", 70, 160);
	}
	

	@Override
	public void keyPressed(int k) {
		if(KeyEvent.VK_SPACE == k) gsm.setPause(false);
		if(KeyEvent.VK_ENTER == k) {
			gsm.setPause(false);
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	public void keyReleased(int k) {

	}

}
