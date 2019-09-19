package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Moeda extends MapObject{

	private boolean remove;
	private BufferedImage[] sprites;
	
	public Moeda(TileMap tm) {
		
		super(tm);
		
		width = 18;
		height = 20;
		cwidth = 6;
		cheight = 10;
		
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/HUD/coin.png"));
			sprites = new BufferedImage[6];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width,	0, width, height);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animacao();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		facingRight = true;
		
	}
	
	
	public void update() {
		
		if(right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if(left && dx == 0) {
			right = true;
			left = false;
			facingRight = true;
		}
		
		animation.update();
	}		

	
	public void draw(Graphics2D g) {
		
		//if(notOnScreen()) return;
		setMapPosition();
		super.draw(g);
	}

	public boolean isRemove() {return remove;}
	public void setRemove() {this.remove = true;}
	
}
