package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.GameStateManager;

public class GamePanel extends JPanel implements Runnable{

	public static final int LARGURA = 320;
	public static final int ALTURA = 240;
	public static final int ESCALA = 2;

	private Thread thread;
	private boolean rodando;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	private BufferedImage imagem;
	private Graphics2D g;

	private GameStateManager gms;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(LARGURA * ESCALA, ALTURA * ESCALA));
		setFocusable(true);
		requestFocus();

	}

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		init();


		long start;
		long elapsed;
		long wait;


		while (rodando) {
			start = System.nanoTime();

			update();
			reader();
			draw();

			elapsed = System.nanoTime() - start;

			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;

			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void init() {
		imagem = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) imagem.getGraphics();

		gms = new GameStateManager();
		
		rodando = true;
	}


	private void update() {
		gms.update();
	}

	private void reader() {
		gms.draw(g);
	}

	private void draw() {
		Graphics g2 = getGraphics();
		g2.drawImage(imagem, 0, 0, LARGURA*ESCALA, ALTURA*ESCALA, null);
		g2.dispose();
	}

	public GameStateManager getGms() {
		return gms;
	}
}
