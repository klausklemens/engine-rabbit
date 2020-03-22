package ninja.klausklemens.adventure;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.util.Arrays;
import javax.swing.*;

public class Board extends JPanel implements Runnable {
	private Image background;
	private Image background_map;
	private Sprite dude;

	Thread animator;

	public Board() {
		initBoard();
		dude = new Sprite();
	}

	private void initBoard() {
		loadImage();

		int w = background.getWidth(this);
		int h =  background.getHeight(this);
		setPreferredSize(new Dimension(w, h));

		setFocusable(true);
		addMouseListener(new MouseAdapter());
		addKeyListener(new KeyAdapter());
	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon("module/assets/livingroom/background.png");
		background = ii.getImage();
		ImageIcon ic = new ImageIcon("module/assets/livingroom/background_map.png");
		background_map = ic.getImage();
	}

	@Override
	public void addNotify() {
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getPreferredSize().width, getPreferredSize().height, null);
		//g.drawImage(background_map, 0, 0, getPreferredSize().width, getPreferredSize().height, null);
		dude.draw(g);

		Toolkit.getDefaultToolkit().sync();
	}

	public long DELAY = 1;
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		boolean loop = true;
		while (loop) {
			dude.move();
			repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				String msg = String.format("Thread interrupted: %s", e.getMessage());

				JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
				loop = false;
			}

			beforeTime = System.currentTimeMillis();
		}
	}

	private class MouseAdapter extends java.awt.event.MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			PixelGrabber pg = new PixelGrabber(background_map, e.getX(), e.getY(), 1, 1, false);
			try {
				pg.grabPixels();
			} catch (InterruptedException ex) {
				return;
			}
			Object pixels = pg.getPixels();
			if (pixels instanceof byte[]) {
				byte[] arr = (byte[]) pixels;
				System.out.println(Arrays.toString(arr));
				if (arr[0] == 0) {
					dude.moveTo(e.getX(), e.getY());
				}
			}
		}
	}

	private class KeyAdapter extends java.awt.event.KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				dude.move();
			}
		}
	}
}
