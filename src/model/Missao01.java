package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import view.GamePanel;

public class Missao01 extends GameState{

	private Font font;
	
	public Missao01(GameStateManager gsm) {
		this.gsm = gsm;
		font = new Font("Arial", Font.PLAIN, 14);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.LARGURA, GamePanel.ALTURA);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("MISSÃO 01", 130, 20);
		g.drawString("Você poderá enfrentar o BOSS, se somente se", 10, 80);
		g.drawString("TODOS os inimigos estiverem mortos.", 45, 100);
		g.drawString("ENTER COMEÇAR O JOGO", 70, 180);
	}
	

	@Override
	public void keyPressed(int k) {
		if(KeyEvent.VK_ENTER == k) {
			gsm.setPause(false);
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
	}

	public void keyReleased(int k) {

	}

}
