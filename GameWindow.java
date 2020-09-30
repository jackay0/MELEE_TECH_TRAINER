import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	private BufferedImage controller = null;
	// sprites for buttons on screen
	private BufferedImage abutton;
	private BufferedImage bbutton;
	private BufferedImage xbutton;
	private BufferedImage ybutton;
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
	private BufferedImage controllerr;
	//private int score;
	//vars dealing specifically with scoring
	private int a;

	private ButtonFlash ButtonFlash;

	private Note note;

	public void init() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try { // try catch means: try to do this, if it cant be done, then catch, in this case
				// an error report
			spriteSheet = loader.LoadImage("/Sprite_Sheet.png");
			controller = loader.LoadImage("/GCC.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Below are the buttonflash objects, which are the white button sprites. They
		// are off screen until a button press
		ButtonFlash = new ButtonFlash(-100, -100, this);

		// These are the images for the regular buttons, not objects because their
		// locations aren't manipulated.
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		SpriteSheet gcc = new SpriteSheet(controller);
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
		controllerr = gcc.grabImage(1, 1, 200, 100);

		note = new Note(327, 0, this);
        
        
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

	public static void main(String[] args) {
		GameWindow game = new GameWindow();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // Dimension class: simply works with width
																				// and height variables to size the
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
			render();
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
		note.tick();
		//double eye = ButtonFlash.getI();

		if(note.getY() < 374 && ButtonFlash.getI()>2)
			ButtonFlash.setI(0.0);
		if (Physics.Collision(note, ButtonFlash) && note.getY() > 380 && ButtonFlash.getI() < 2.0) {
			a= a+1;
			note.setY(0);
			// System.out.println(score);
		}
		

	}

	private void render() // everything in the game that renders
	{
		// creates a buffer strategy that handles all the buffering behind the scenes
		BufferStrategy bs = this.getBufferStrategy(); // returns a BufferStrategy
		if (bs == null) {
			createBufferStrategy(3); // We are going to have 3 buffers which increases loading speed over time
			return;
		}
		int score = a;
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(controllerr, 200, 350, this);
		g.drawImage(abutton, 327, 367, this);
		g.drawImage(bbutton, 313, 372, this);
		g.drawImage(xbutton, 339, 356, this);
		g.drawImage(ybutton, 319, 353, this);
		g.drawImage(lbutton, 238, 336, this);
		g.drawImage(rbutton, 327, 336, this);
		g.drawImage(startbutton, 282, 375, this);
		g.drawImage(zbutton, 327, 345, this);
		g.drawImage(stick, 244, 364, this);
		g.drawImage(cstick, 307, 397, this); // UHJK

		g.drawString("Score:" + score, 10, 10);

		ButtonFlash.render(g);

		note.render(g);
		
		//////////////////////////////////// where we can draw images ^^^^^

		g.dispose();
		bs.show();

	}

	public BufferedImage getSpriteSheet() // this is a getter that will allow us to access the spritesheet from any
											// other class
	{
		return spriteSheet;
	}

	public ButtonFlash getButtonFlash() {
		return ButtonFlash;
	}
	public double getDistance()
	{
		
		return note.getY() - ButtonFlash.getY();
	}
	// This class is used for the ButtonFlash class, enabling communication with the
	// keyboard and the class
	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			ButtonFlash.keyPressed(e);
			note.keyPressed(e); //no released because it is necessary for something you press to pause and unpause
		
		}

		@Override
		public void keyReleased(KeyEvent e) {
			ButtonFlash.keyReleased(e);
			// anote.keyReleased(e);
			// anote.setFalling(false);
		}

	}
}