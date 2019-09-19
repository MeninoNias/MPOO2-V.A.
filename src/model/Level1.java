package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.GamePanel;

public class Level1 extends GameState{

	private TileMap tileMap;
	private Background bg;

	private HUD hud;

	private ArrayList<Inimigo> inimigos;
	private ArrayList<Explosao> explosaos;

	private Player player;
	private ArrayList<Moeda> moedas;

	private InimigoBoss b;
	private boolean boss;
	
	public Level1(GameStateManager gms) {
		this.gsm = gms;
		init();
	}

	@Override
	public void init() {

		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("Level01.txt");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		bg = new Background("/Backgrounds/menubg.gif", 0.1);

		player = new Player(tileMap);
		player.setPosition(100, 100);

		populateEnemies();
		populateMoedas();
		explosaos = new ArrayList<Explosao>();

		hud = new HUD(player);
	}

	private void populateEnemies() {

		inimigos = new ArrayList<Inimigo>();
		
		InimigoSlugger s;
		Point[] points = new Point[] {
				new Point(300, 200),
				new Point(275, 200),
				new Point(250, 200),
				new Point(860, 200),
				new Point(835, 200),
				new Point(1000, 200),
				new Point(1025, 200),
				new Point(1525, 200),
				new Point(1680, 200),
				new Point(1800, 200)
		};
		for(int i = 0; i < points.length; i++) {
			s = new InimigoSlugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			inimigos.add(s);
		}
		
	}
	
	private void populateMoedas() {
		moedas = new ArrayList<Moeda>();
		Moeda m;
		Point[] points = new Point[] {
				new Point(275, 100),
				new Point(250, 100),
				new Point(225, 100),
				new Point(830, 120),
				new Point(805, 120),
				new Point(1010, 100),
				new Point(1035, 100),
				new Point(1200, 70),
				new Point(1230, 70),
				new Point(1510, 150),
				new Point(1650, 150),
				new Point(2050, 100),
				new Point(2075, 110),
				new Point(2100, 120),
				new Point(2275, 70),
				new Point(2300, 70),
				new Point(1780, 150)
		};
		for(int i = 0; i < points.length; i++) {
			m = new Moeda(tileMap);
			m.setPosition(points[i].x, points[i].y);
			moedas.add(m);
		}
	}
	
	private void reset() {
		player.reset();
		player.setPosition(100, 200);
		
		inimigos.clear();
		moedas.clear();
		
		populateMoedas();
		populateEnemies();
	}
	
	public void update() {

		if(player.isDead()) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
		
		if(player.getHealth() == 0 || player.gety() > tileMap.getHeight() - player.getWidth()/2) {
			reset();
		}
		
		if( player.getx() == 2740 ) {
			if(inimigos.size() == 0) {
				try {
					Thread.sleep(2000);
					
					player.setPosition(3030, 200);
					
					b = new InimigoBoss(tileMap);
					b.setPosition(3000, 200);
					inimigos.add(b);
					
					boss = true;
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(boss) {
			if(b.isDead()) {
				int k = JOptionPane.showConfirmDialog(null, "Deseja Jogar Novamente?", "Game", JOptionPane.YES_NO_OPTION);
				if(k == 0) {
					boss = false;
					player.setLife(4);
					reset();
				}
				else {
					gsm.setState(GameStateManager.MENUSTATE);
				}
				
			}
		}
		
		player.update();
		tileMap.setPosition(GamePanel.LARGURA / 2 - player.getx(), GamePanel.ALTURA / 2 - player.gety());

		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		player.checkAttack(inimigos);
		player.checkMoedasColisao(moedas);
		
		for(int i = 0; i < moedas.size(); i++) {
			Moeda m = moedas.get(i);
			m.update();
			if(m.isRemove()) {
				player.setScore(player.getScore() + (m.getWidth()/2) * 10);
				moedas.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo e = inimigos.get(i);
			e.update();
			if(e.isDead()) {
				player.setScore(player.getScore() + (e.getWidth()/2) * 5);
				inimigos.remove(i);
				i--;
				explosaos.add(new Explosao(e.getx(), e.gety()));
			}
		}
		
		for(int i = 0; i < explosaos.size(); i++) {
			explosaos.get(i).update();
			if(explosaos.get(i).shouldRemove()) {
				explosaos.remove(i);
				i--;
			}
		}
	}

	public void draw(Graphics2D g) {
		bg.draw(g);

		tileMap.draw(g);

		player.draw(g);

		for(int i = 0; i < moedas.size(); i++) {
			moedas.get(i).draw(g);
		}
		
		for(int i = 0; i < inimigos.size(); i++) {
			inimigos.get(i).draw(g);
		}
		
		for(int i = 0; i < explosaos.size(); i++) {
			explosaos.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			explosaos.get(i).draw(g);
		}
		
		hud.draw(g);
	}

	public void keyPressed(int k) {
		if(KeyEvent.VK_SPACE == k) gsm.setPause(true);
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
	}

}
