package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import view.GamePanel;

public class AjudaMenu extends GameState{

	private Font font;
	
	public AjudaMenu(GameStateManager gsm) {
		this.gsm = gsm;
		font = new Font("Arial", Font.PLAIN, 11);
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
		g.drawString("Ajuda - Botões", 125, 20);
		g.drawString("<- e -> - Movimento esqueda e direita do Player", 10, 70);
		g.drawString("W - Pulo", 10, 90);
		g.drawString("W + E - O player utiliza as azas para planar", 10, 110);
		g.drawString("R - Ataca com arranhão", 10, 130);
		g.drawString("F - Lança uma bola de fogo", 10, 150);
		g.drawString("Espaço - Pausar o Game", 10, 170);
		g.setFont(new Font("Arial", Font.PLAIN, 8));
		g.drawString("2019.1", 285, 235);
	}
	

	@Override
	public void keyPressed(int k) {
		if(KeyEvent.VK_ENTER == k) {
			gsm.setPause(false);
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	public void keyReleased(int k) {

	}

}