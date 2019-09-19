package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import view.GamePanel;

public class Background {

	private BufferedImage imagem;
	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveEscala;

	public Background(String s, double ms) {

		try {
			imagem = ImageIO.read(
					getClass().getResourceAsStream(s)
					);
			moveEscala = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void setPosition(double x, double y) {
		this.x = (x * moveEscala) % GamePanel.WIDTH;
		this.y = (y * moveEscala) % GamePanel.HEIGHT;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void draw(Graphics2D g) {
		g.drawImage(imagem, (int)x, (int)y, null);
		
		if(x<0) {
			g.drawImage(imagem, (int) x + GamePanel.LARGURA, (int) y, null); 
		}
		if(x>0) {
			g.drawImage(imagem, (int) x - GamePanel.LARGURA, (int) y, null); 
		}
	}
}