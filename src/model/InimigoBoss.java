package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class InimigoBoss extends Inimigo{

private BufferedImage[] sprites;
	
	public InimigoBoss(TileMap tm) {
		
		super(tm);
		
		moveSpeed = 0.5;
		maxSpeed = 0.5;
		fallSpeed = 0.2;
		maxFallSpeed = 5.0;
		
		width = 64;
		height = 64;
		cwidth = 32;
		cheight = 32;
			
		health = maxHealth = 25;
		damage = 2;
		
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/boss.png"));
			sprites = new BufferedImage[1];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animacao();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
		
	}
	
	private void getNextPosition() {
		
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		
		if(falling) {
			dy += fallSpeed;
		}
		
	}
	
	public void update() {
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(flinching) {
			long elapsed =(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}
		
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
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
}
