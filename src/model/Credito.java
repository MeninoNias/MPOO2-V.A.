package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import view.GamePanel;

public class Credito extends GameState{
	private Font font;
	
	public Credito(GameStateManager gsm) {
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
		g.drawString("UFRPE - UAST", 125, 20);
		g.drawString("Orientando : Ananias Raphael da Silva Nobrega", 10, 95);
		g.drawString("Orientador : Prof. Dr. Richarlyson Alves D'Emery", 10, 115);
		g.drawString("MPOO - Modelagem de Progamação Orientada a Objeto", 20, 170);
		g.setFont(new Font("Arial", Font.PLAIN, 8));
		g.drawString("2019.1", 285, 235);	}
	

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

