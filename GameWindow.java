
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameWindow extends Canvas implements Runnable { // This interface is useful when utilizing multiple threads
	// In this case, it ensures that just because one Thread has been executed,
	// another won't simply stop its processes
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Melee Tech Trainer";

	private boolean running = false;
	private Thread thread;

	// sound files
	private File pp = new File("/C://Users//0001081009//workspace//melee//src//pp.wav");

	int pause;

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

	// vars dealing specifically with scoring
	private int a;

	private ButtonFlash ButtonFlash;
	private ButtonFlash abutton2; // sprite for white a button
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
	private ButtonFlash cUp2;
	private ButtonFlash cDown2;
	private ButtonFlash cLeft2;
	private ButtonFlash cRight2;
	private ButtonFlash cURight2;
	private ButtonFlash cULeft2;
	private ButtonFlash cDRight2;
	private ButtonFlash cDLeft2;

	private Note Anote;
	private Note Bnote;
	private Note Xnote;
	private Note Ynote;
	private Note Lnote;
	private Note Rnote;
	private Note Znote;
	private Note stickUpnote;
	private Note cUpnote;
	private Note stickDLeftnote;
	private Note stickDownnote;

	// Fox Tech
	JRadioButtonMenuItem FoxWavedash;
	JRadioButtonMenuItem multi;

	JRadioButtonMenuItem MarthWavedash;

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

		Anote = new Note("a", 327, -40, this);
		Bnote = new Note("b", 313, -35, this);
		Xnote = new Note("x", 339, -51, this);
		Ynote = new Note("y", 319, -54, this);
		Lnote = new Note("l", 238, -71, this);
		Rnote = new Note("r", 328, -71, this);
		Znote = new Note("z", 327, -62, this);
		cUpnote = new Note("cUp", 307, -10, this);
		stickUpnote = new Note("stickUp", 244, -43, this);
		stickDLeftnote = new Note("stickDLeft", 244, -43, this);
		stickDownnote = new Note("stickDown", 244, -43, this);

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
		JTextArea output = new JTextArea(5, 45);
		output.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(output); // and height variables to size the
		JPanel contentPane = new JPanel(new BorderLayout());

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
		contentPane.add(scrollPane, BorderLayout.CENTER);
		frame.setJMenuBar(game.createMenuBar());
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
				if (pause == 0)
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

	public void delayFalling() {

		// FOX WAVEDASH
		if (FoxWavedash.isSelected()) {
			if (Ynote.getFalling() == false && Rnote.getFalling() == false && stickDLeftnote.getFalling() == false) {
				Ynote.setFalling(true);
				Rnote.setFalling(true);
				stickDLeftnote.setFalling(true);
			}
		}
		// FOX MULTISHINE
				if (multi.isSelected()) {
					if (Bnote.getFalling() == false && Xnote.getFalling() == false && stickDownnote.getFalling() == false) {
						Bnote.setFalling(true);
						Xnote.setFalling(true);
						stickDownnote.setFalling(true);
					}
				}
	}

	private void tick() // everything in the game that updates
	{

		// FOX WAVEDASH

		if (FoxWavedash.isSelected()) {

			delayFalling();

			Ynote.tick();

			Rnote.tick();

			stickDLeftnote.tick();

			if (Bnote.getY() < 370 && bbutton2.getI() > 2)
				bbutton2.setI(0.0);
			if (Physics.Collision(Bnote, bbutton2) && Bnote.getY() > 372 && bbutton2.getI() < 2.0
					&& bbutton2.getX() == 313) {
				a = a + 1;
				// Bnote.setY(-35); //set falling to false
				PlaySound(pp);
				// System.out.println(score);

			}
			if (Anote.getY() < 365 && abutton2.getI() > 2)
				abutton2.setI(0.0);
			if (Physics.Collision(Anote, abutton2) && Anote.getY() > 367 && abutton2.getI() < 2.0
					&& abutton2.getX() == 327) {
				a = a + 1;
				// Anote.setY(-40);
				PlaySound(pp);
				// System.out.println(score);
			}
			if (Ynote.getY() < 351 && ybutton2.getI() > 2)
				ybutton2.setI(0.0);
			if (Physics.Collision(Ynote, ybutton2) && Ynote.getY() > 353 && ybutton2.getI() < 2.0
					&& ybutton2.getX() == 319) {
				a = a + 1;
				Ynote.setY(-54);
				PlaySound(pp);
				// System.out.println(score);
				Ynote.setFalling(false);
			}
			if (Rnote.getY() < 334 && rbutton2.getI() > 2)
				rbutton2.setI(0.0);
			if (Physics.Collision(Rnote, rbutton2) && Rnote.getY() > 336 && rbutton2.getI() < 2.0
					&& rbutton2.getX() == 328) {
				a = a + 1;
				Rnote.setY(-71);
				PlaySound(pp);
				// System.out.println(score);
				Rnote.setFalling(false);
			}
			if (stickDLeftnote.getY() < 362 && stickDLeft2.getI() > 2)
				stickDLeft2.setI(0.0);
			if (Physics.Collision(stickDLeftnote, stickDLeft2) && stickDLeftnote.getY() > 364
					&& stickDLeft2.getI() < 2.0 && stickDLeft2.getX() == 244) {
				a = a + 1;
				stickDLeftnote.setY(-43);
				PlaySound(pp);
				// System.out.println(score);
				stickDLeftnote.setFalling(false);
			}
		}

		// MULTISHINE
		if (multi.isSelected()) {
			delayFalling();

			Xnote.tick();

			Bnote.tick();
			
			stickDownnote.tick();
			
			if (Bnote.getY() < 370 && bbutton2.getI() > 2)
				bbutton2.setI(0.0);
			if (Physics.Collision(Bnote, bbutton2) && Bnote.getY() > 372 && bbutton2.getI() < 2.0
					&& bbutton2.getX() == 313) {
				a = a + 1;
				// Bnote.setY(-35); //set falling to false
				PlaySound(pp);
				// System.out.println(score);

			}
			if (Xnote.getY() < 351 && xbutton2.getI() > 2)
				xbutton2.setI(0.0);
			if (Physics.Collision(Xnote, xbutton2) && Xnote.getY() > 353 && xbutton2.getI() < 2.0
					&& xbutton2.getX() == 319) {
				a = a + 1;
				Xnote.setY(-54);
				PlaySound(pp);
				// System.out.println(score);
				Xnote.setFalling(false);
			}
			if (stickDownnote.getY() < 362 && stickDLeft2.getI() > 2)
				stickDown2.setI(0.0);
			if (Physics.Collision(stickDownnote, stickDown2) && stickDownnote.getY() > 364
					&& stickDown2.getI() < 2.0 && stickDown2.getX() == 244) {
				a = a + 1;
				stickDownnote.setY(-43);
				PlaySound(pp);
				// System.out.println(score);
				stickDownnote.setFalling(false);
			}

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
		g.drawImage(rbutton, 328, 336, this);
		g.drawImage(startbutton, 282, 375, this);
		g.drawImage(zbutton, 327, 345, this);
		g.drawImage(stick, 244, 364, this);
		g.drawImage(cstick, 307, 397, this); // UHJK

		g.drawString("Score:" + score, 10, 10);

		// Buttons on the controller
		abutton2.render(g);
		bbutton2.render(g);
		xbutton2.render(g);
		ybutton2.render(g);
		lbutton2.render(g);
		rbutton2.render(g);
		startbutton2.render(g);
		zbutton2.render(g);
		stickDLeft2.render(g);
		//Anote.render(g);

		if (multi.isSelected()) {
			Bnote.render(g);
			Xnote.render(g);
			stickDownnote.render(g);
		}

		// We need to divide the buttons that comprise of each tech into this render, so
		// that they only are on screen when selected
		if (FoxWavedash.isSelected()) {
			Ynote.render(g);
			Rnote.render(g);
			stickDLeftnote.render(g);
		}

		//Lnote.render(g);
		//Znote.render(g);
		// cUpnote.render(g);
		//stickUpnote.render(g);

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

	public double getDistance() {

		return Anote.getY() - ButtonFlash.getY();
	}

	// This class is used for the ButtonFlash class, enabling communication with the
	// keyboard and the class
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
			stickDLeft2.keyPressed(e);

			Anote.keyPressed(e); // no released because it is necessary for something you press to pause and
									// unpause
			Bnote.keyPressed(e);
			Xnote.keyPressed(e);
			Ynote.keyPressed(e);
			Lnote.keyPressed(e);
			Rnote.keyPressed(e);
			Znote.keyPressed(e);
			stickUpnote.keyPressed(e);
			stickDLeftnote.keyPressed(e);
			stickDownnote.keyPressed(e);

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

			if (e.getKeyCode() == KeyEvent.VK_S) {
				pause = 1;
			} else {
				pause = 0;
			}
			zbutton2.keyReleased(e);
			stickDLeft2.keyReleased(e);
			// anote.keyReleased(e);
			// anote.setFalling(false);
		}

	}

	// plays the sound

	static void PlaySound(File sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Menu Bar at the top of the window

	public JMenuBar createMenuBar() {
		// Characters and main menus
		JMenuBar menuBar;
		JMenu menu;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Menus
		menu = new JMenu("MENU    	");

		menuBar.add(menu);

		// menu.addSeparator();

		ButtonGroup group = new ButtonGroup();

		FoxWavedash = new JRadioButtonMenuItem("Fox Wavedash");

		FoxWavedash.setSelected(true);
		group.add(FoxWavedash);
		menu.add(FoxWavedash);

		MarthWavedash = new JRadioButtonMenuItem("Marth Wavedash");

		multi = new JRadioButtonMenuItem("Multishine");
		group.add(multi);
		menu.add(multi);

		return menuBar;
	}

}