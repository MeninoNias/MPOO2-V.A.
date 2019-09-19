package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

	private Background bg;

	private GameStateManager gms;

	private int escolhaAtual = 0;
	private String[] opicoes = {
			"COMEÇAR",
			"MULTIPLAYER",
			"AJUDA",
			"CREDÍTOS",
			"SAIR"
	};

	private Color tituloColor;
	private Font tituloFont;

	private Font font;

	public MenuState(GameStateManager gms) {

		this.gms = gms;

		try {

			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);

			tituloColor = new Color(128, 0, 0);
			tituloFont = new Font("Arial", Font.PLAIN, 28);

			font = new Font("Arial", Font.PLAIN, 12);

		}catch (Exception e) {System.out.println("Imagem não encontrada !!");}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		g.setColor(tituloColor);
		g.setFont(tituloFont);
		g.drawString("ADVENTURE", 80, 70);
		
		g.setFont(font);
		for( int i =0; i < opicoes.length; i++) {
			if(i == escolhaAtual) {
				g.setColor(Color.BLACK);
			}else {
				g.setColor(Color.RED);
			}
			g.drawString(opicoes[i], 145, 140 + i * 15);
		}
	}
	
	private void menu() {
		if(escolhaAtual == 0) {
			gms.setState(GameStateManager.MISSAO);
		}
		if(escolhaAtual == 1) {
		}
		if(escolhaAtual == 2) {
			gms.setState(GameStateManager.AJUDASTATES);
		}
		if(escolhaAtual == 3) {
			gms.setState(GameStateManager.CREDITOS);
		}
		if(escolhaAtual == 4) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			menu();
		}
		if(k == KeyEvent.VK_UP) {
			escolhaAtual--;
			if(escolhaAtual == -1) {
				escolhaAtual = opicoes.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			escolhaAtual++;
			if(escolhaAtual == opicoes.length) {
				escolhaAtual = 0;
			}
		}
	}

	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
