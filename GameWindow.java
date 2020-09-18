import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameWindow extends Canvas implements Runnable { // This interface is useful when utilizing multiple threads
	// In this case, it ensures that just because one Thread has been executed,
	// another won't simply stop its processes
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Melee Tech Trainer";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;

	// temp
	private BufferedImage abutton; // sprite for a button
	private BufferedImage bbutton; // sprite for b button
	private BufferedImage xbutton; // sprite for x button
	private BufferedImage ybutton; // sprite for y button
	private BufferedImage lbutton;
	private BufferedImage rbutton;
	private BufferedImage startbutton;
	private BufferedImage zbutton;
	private BufferedImage stick;
	private BufferedImage stickUp;
	private BufferedImage stickDown;
	private BufferedImage stickLeft;
	private BufferedImage stickRight;
	private BufferedImage stickURight;
	private BufferedImage stickULeft;
	private BufferedImage stickDRight;
	private BufferedImage stickDLeft;
	private BufferedImage cstick;
	private BufferedImage GCC = null;
	private BufferedImage controller;

	private ButtonFlash abutton2;
	private ButtonFlash bbutton2;
	private ButtonFlash xbutton2;
	private ButtonFlash ybutton2;
	private ButtonFlash lbutton2;
	private ButtonFlash rbutton2;
	private ButtonFlash startbutton2;
	private ButtonFlash zbutton2;

	private ButtonFlash stickUp2;
	private ButtonFlash stickDown2;
	private ButtonFlash stickLeft2;
	private ButtonFlash stickRight2;
	private ButtonFlash stickURight2;
	private ButtonFlash stickULeft2;
	private ButtonFlash stickDRight2;
	private ButtonFlash stickDLeft2;

	private ButtonFlash cDown2;
	private ButtonFlash cUp2;
	private ButtonFlash cLeft2;
	private ButtonFlash cRight2;
	private ButtonFlash cURight2;
	private ButtonFlash cULeft2;
	private ButtonFlash cDRight2;
	private ButtonFlash cDLeft2;
	

	private Note anote;

	public void init() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try { // try catch means: try to do this, if it can't be done, then catch, in this case
				// an error report
			spriteSheet = loader.LoadImage("/Sprite_Sheet.png");
			GCC = loader.LoadImage("/GCC.png");
			//GCC = ImageIO.read(getClass().getResource("/GCC.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		abutton2 = new ButtonFlash(-100, -100, this);
		bbutton2 = new ButtonFlash(-100, -100, this);
		xbutton2 = new ButtonFlash(-100, -100, this);
		ybutton2 = new ButtonFlash(-100, -100, this);
		lbutton2 = new ButtonFlash(-100, -100, this);
		rbutton2 = new ButtonFlash(-100, -100, this);
		startbutton2 = new ButtonFlash(-100, -100, this);
		zbutton2 = new ButtonFlash(-100, -100, this);
		stickUp2 = new ButtonFlash(-100, -100, this);
		stickDown2 = new ButtonFlash(-100, -100, this);
		stickLeft2 = new ButtonFlash(-100, -100, this);
		stickRight2 = new ButtonFlash(-100, -100, this);
		stickURight2 = new ButtonFlash(-100, -100, this);
		stickULeft2 = new ButtonFlash(-100, -100, this);
		stickDRight2 = new ButtonFlash(-100, -100, this);
		stickDLeft2 = new ButtonFlash(-100, -100, this);
		cUp2 = new ButtonFlash(-100, -100, this);
		cDown2 = new ButtonFlash(-100, -100, this);
		cLeft2 = new ButtonFlash(-100, -100, this);
		cRight2 = new ButtonFlash(-100, -100, this);
		cURight2 = new ButtonFlash(-100, -100, this);
		cULeft2 = new ButtonFlash(-100, -100, this);
		cDRight2 = new ButtonFlash(-100, -100, this);
		cDLeft2 = new ButtonFlash(-100, -100, this);
		
		SpriteSheet gcc = new SpriteSheet(GCC);
		controller = gcc.grabController(0, 0, 200, 100);
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		abutton = ss.grabImage(1, 1, 32, 32);
		bbutton = ss.grabImage(2, 1, 32, 32);
		xbutton = ss.grabImage(4, 1, 32, 32);
		ybutton = ss.grabImage(3, 1, 32, 32);
		lbutton = ss.grabImage(5, 1, 32, 32);
		rbutton = ss.grabImage(6, 1, 32, 32);
		startbutton = ss.grabImage(8, 1, 32, 32);
		zbutton = ss.grabImage(7, 1, 32, 32);
		stick = ss.grabImage(1, 3, 32, 32);
		stickUp = ss.grabImage(5, 2, 32, 32);
		stickDown = ss.grabImage(2, 2, 32, 32);
		stickLeft = ss.grabImage(7, 2, 32, 32);
		stickRight = ss.grabImage(8, 2, 32, 32);
		stickURight = ss.grabImage(6, 2, 32, 32);
		stickULeft = ss.grabImage(4, 2, 32, 32);
		stickDRight = ss.grabImage(3, 2, 32, 32);
		stickDLeft = ss.grabImage(1, 2, 32, 32);
		cstick = ss.grabImage(2, 4, 32, 32);

		anote = new Note(400, 60, this);

	}

	private synchronized void start() { // synchronization is an important thing when dealing with multiple Threads
		if (running) { // It defeats the purpose of working with multiple threads and errors occur
						// without ensuring this
			return; // The following two methods ensure that threads are both serialized and
					// executed
		}
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	private synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);

	}

	public static void main(String[] args) throws IOException {
		GameWindow game = new GameWindow();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Dimension class: simply works with width
		//BufferedImage GCC = ImageIO.read(GameWindow.class.getClassLoader().getResourceAsStream("GCC.png"));																		// and height variables to size the
																				// window
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setFocusable(false); // very important
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack(); // not sure what this does, supposedly an optimization
		frame.addKeyListener(game.new AL());
		game.start();

	}

	public void run() { // it is necessary to check if the program is 'running' or not because we
						// implement runnable
		// this 'game loop' ensures it will run well on different computers, a lot of
		// stuff will happen here: rendering, moving stuff, etc.
		init();

		long timerLast = System.nanoTime();
		final double ticks = 60.0; // every time it goes through the loop it will update 60 times
		double ns = 1000000000 / ticks;
		double chng = 0; // gets the time past, so it if fps or ticks run behind it will catch itself up
		int updater = 0; // # of ticks
		int fps = 0; // fps
		long time = System.currentTimeMillis();

		while (running) {
			long current = System.nanoTime(); // because it takes time for it to get from outside the loop to inside, we
												// don't want a small desync, the following
			chng += (current - timerLast) / ns; // the difference is null and the game is caught up
			timerLast = current;
			if (chng >= 1) {
				tick();
				updater++;
				chng--;
			}
			try {
				render();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fps++;

			if (System.currentTimeMillis() - time > 1000) {
				time += 1000;
				System.out.println(updater + "Ticks. FPS:" + fps);
				updater = 0;
				fps = 0;
			}

			// System.out.println("WORKING");
		}
		stop();
	}

	private void tick() // everything in the game that updates
	{

	}

	private void render() throws IOException // everything in the game that renders
	{
		// creates a buffer strategy that handles all the buffering behind the scenes
		BufferStrategy bs = this.getBufferStrategy(); // returns a BufferStrategy
		if (bs == null) {
			createBufferStrategy(3); // We are going to have 3 buffers which increases loading speed over time
			return;
		}
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////
		g.drawImage(controller, 0, 0, 200,100, this);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(abutton, 400, 400, this); // this was to test and see if we could access an individual sprite\
		g.drawImage(bbutton, 360, 420, this);
		g.drawImage(xbutton, 440, 385, this);
		g.drawImage(ybutton, 385, 370, this);
		g.drawImage(lbutton, 250, 340, this);
		g.drawImage(rbutton, 405, 340, this);
		g.drawImage(startbutton, 300, 420, this);
		g.drawImage(zbutton, 390, 350, this);
		g.drawImage(stick, 245, 390, this);
		g.drawImage(cstick, 390, 440, this); // UHJK

		abutton2.render(g);
		bbutton2.render(g);
		xbutton2.render(g);
		ybutton2.render(g);
		lbutton2.render(g);
		rbutton2.render(g);
		startbutton2.render(g);
		zbutton2.render(g);
		stickUp2.render(g);
		stickDown2.render(g);
		stickRight2.render(g);
		stickURight2.render(g);
		stickULeft2.render(g);
		stickDRight2.render(g);
		stickDLeft2.render(g);
		cUp2.render(g);
		cDown2.render(g);
		cLeft2.render(g);
		cRight2.render(g);
		cURight2.render(g);
		cULeft2.render(g);
		cDRight2.render(g);
		cDLeft2.render(g);

		anote.render(g);

		//////////////////////////////////// where we can draw images ^^^^^

		g.dispose();
		bs.show();

	}

	public BufferedImage getSpriteSheet() // this is a getter that will allow us to access the spritesheet from any
											// other class
	{
		return spriteSheet;
	}

	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			abutton2.keyPressed(e);
			bbutton2.keyPressed(e);
			xbutton2.keyPressed(e);
			ybutton2.keyPressed(e);
			lbutton2.keyPressed(e);
			rbutton2.keyPressed(e);
			startbutton2.keyPressed(e);
			zbutton2.keyPressed(e);
			
			stickUp2.keyPressed(e);
			stickDown2.keyPressed(e);
			stickLeft2.keyPressed(e);
			stickRight2.keyPressed(e);
			stickURight2.keyPressed(e);
			stickULeft2.keyPressed(e);
			stickDRight2.keyPressed(e);
			stickDLeft2.keyPressed(e);

			cUp2.keyPressed(e); 
			cDown2.keyPressed(e);
			cLeft2.keyPressed(e);
			cRight2.keyPressed(e);
			cURight2.keyPressed(e);
			cULeft2.keyPressed(e);
			cDRight2.keyPressed(e);
			cDLeft2.keyPressed(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {
			abutton2.keyReleased(e);
			bbutton2.keyReleased(e);
			xbutton2.keyReleased(e);
			ybutton2.keyReleased(e);
			lbutton2.keyReleased(e);
			rbutton2.keyReleased(e);
			startbutton2.keyReleased(e);
			zbutton2.keyReleased(e);
			
			stickUp2.keyReleased(e);
			stickDown2.keyReleased(e);
			stickLeft2.keyReleased(e);
			stickRight2.keyReleased(e);
			stickURight2.keyReleased(e);
			stickULeft2.keyReleased(e);
			stickDRight2.keyReleased(e);
			stickDLeft2.keyReleased(e);
			
			cUp2.keyReleased(e);
			cDown2.keyReleased(e);
			cLeft2.keyReleased(e);
			cRight2.keyReleased(e);
			cURight2.keyReleased(e);
			cULeft2.keyReleased(e);
			cDRight2.keyReleased(e);
			cDLeft2.keyReleased(e);
		}

	}
}
