
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
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
import com.game.src.main.classes.SaveFile;

public class GameWindow extends Canvas implements Runnable { // This interface is useful when utilizing multiple threads
	// In this case, it ensures that just because one Thread has been executed,
	// another won't simply stop its processes
	// 320 x 240 pixels
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Melee Tech Trainer";

	private static SaveFile hs;

	private boolean running = false;
	private Thread thread;

	// sound files
	private File aSound = new File("./Notes/A.wav");
	private File cSound = new File("./Notes/C.wav");

	private int pause;
	private int score;
	// int mx = 0;
	// int my = 0;

	private BufferedImage currentBackground = null;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage controller = null;
	private BufferedImage pauseSheet = null;
	private BufferedImage FDbackground = null;

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

	// EXTRAS
	private BufferedImage controllerr;
	private BufferedImage pauseIcon;
	private BufferedImage title;
	private BufferedImage FDBackground;

	// vars dealing specifically with scoring
	private int a = 0;
	private int b = 0;
	private int c = 0;
	private int counter = 1;
	private int highscore;
	

	int c1 = 0;
	int c2 = 0;
	int c3 = 0;

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

	// ButtonFlashSticks
	private ButtonFlashSticks stickUp3;
	private ButtonFlashSticks stickDown3;
	private ButtonFlashSticks stickLeft3;
	private ButtonFlashSticks stickRight3;
	private ButtonFlashSticks stickURight3;
	private ButtonFlashSticks stickULeft3;
	private ButtonFlashSticks stickDRight3;
	private ButtonFlashSticks stickDLeft3;
	private ButtonFlashSticks cUp3;
	private ButtonFlashSticks cDown3;
	private ButtonFlashSticks cLeft3;
	private ButtonFlashSticks cRight3;
	private ButtonFlashSticks cURight3;
	private ButtonFlashSticks cULeft3;
	private ButtonFlashSticks cDRight3;
	private ButtonFlashSticks cDLeft3;

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
	JRadioButtonMenuItem nair;
	JRadioButtonMenuItem FoxWavedashOOS;
	JRadioButtonMenuItem shortHopDL;
	JRadioButtonMenuItem ledgeDash;

	// WAVEDASH

	private Note YnoteFW;
	private Note RnoteFW;
	private Note stickDLeftnoteFW;

	// MULTISHINE
	private Note BnoteMS;
	private Note XnoteMS;
	private Note stickDownnoteMS;
	private Note stickDownnoteMS2;
	private Note stickDownnoteMS3;

	// NAIR
	private Note AnoteN;
	private Note RnoteN;
	private Note stickDownnoteN;
	private Note YnoteN;

	// Ledgedash
	private Note stickDRightnoteLD;
	private Note YnoteLD;
	private Note stickleftnoteLD;
	private Note RnoteLD;

	// Short hop double laser
	private Note BnoteSHDL;

	// difficulties
	JRadioButtonMenuItem EasyMode;
	JRadioButtonMenuItem MediumMode;
	JRadioButtonMenuItem HardMode;

	// }

	private enum STATE {
		START, PLAY, PRESENTSCORE, BACKGROUNDS
	};

	private STATE state = STATE.START;

	public void init() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try { // try catch means: try to do this, if it cant be done, then catch, in this case
				// an error report
			spriteSheet = loader.LoadImage("/Sprite_Sheet.png");
			controller = loader.LoadImage("/GCC.png");
			pauseSheet = loader.LoadImage("/pause.png");
			title = loader.LoadImage("/title.png");
			FDbackground = loader.LoadImage("./FD.png"); ///
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

		// ButtonFlashSticks
		stickUp3 = new ButtonFlashSticks(-100, -100, this);
		stickDown3 = new ButtonFlashSticks(-100, -100, this);
		stickLeft3 = new ButtonFlashSticks(-100, -100, this);
		stickRight3 = new ButtonFlashSticks(-100, -100, this);
		stickURight3 = new ButtonFlashSticks(-100, -100, this);
		stickULeft3 = new ButtonFlashSticks(-100, -100, this);
		stickDRight3 = new ButtonFlashSticks(-100, -100, this);
		stickDLeft3 = new ButtonFlashSticks(-100, -100, this);

		// These are the images for the regular buttons, not objects because their
		// locations aren't manipulated.
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		SpriteSheet gcc = new SpriteSheet(controller);
		SpriteSheet p = new SpriteSheet(pauseSheet);
		SpriteSheet t = new SpriteSheet(title);
		SpriteSheet fd = new SpriteSheet(FDbackground);
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

		// EXTRAS
		controllerr = gcc.grabImage(1, 1, 200, 100);
		pauseIcon = p.grabImage(1, 1, 32, 32);
		title = t.grabImage(1, 1, 32, 32);
		FDBackground = fd.grabImage(1, 1, 320, 240);

		// INITIALIZES notes for each tech, varies in position depending on the
		// technique

		// WAVEDASH
		// at frame four of the jump is the airdodge, aka 4/60 of a second, aka 1/15 of
		// a second
		// the note is moved BY the tick method, which y=y+1 every 1/60th of a second
		// therefore, y of the downleft and r is 4 y places away from the y?
		// this doesn't necessarily work because the hitboxes are 32 x 32, half of it is
		// 16, add 4?20?
		RnoteFW = new Note("r", 348, -74, this);
		YnoteFW = new Note("y", 339, -54, this);
		stickDLeftnoteFW = new Note("stickDLeft", 264, -76, this);

		// MULTISHINE
		BnoteMS = new Note("b", 333, -35, this);
		XnoteMS = new Note("x", 359, -54, this);
		stickDownnoteMS = new Note("stickDown", 264, -33, this);
		stickDownnoteMS2 = new Note("stickDown", 264, -48, this);
		stickDownnoteMS3 = new Note("stickDown", 264, -63, this);

		// NAIR
		AnoteN = new Note("a", 347, -40, this);
		stickDownnoteN = new Note("stickDown", 264, -30, this);
		RnoteN = new Note("r", 348, -71, this);
		YnoteN = new Note("y", 339, -10, this);

		// Ledgedash
		RnoteLD = new Note("r", 348, -71, this);
		YnoteLD = new Note("y", 339, -54, this);
		stickDRightnoteLD = new Note("stickDRight", 264, -43, this);
		stickleftnoteLD = new Note("stickLeft", 264, -43, this);

		// Short hop double laser

	/*	Anote = new Note("a", 347, -40, this);
		Bnote = new Note("b", 333, -35, this);
		Xnote = new Note("x", 359, -51, this);
		Ynote = new Note("y", 339, -54, this);
		Lnote = new Note("l", 258, -71, this);
		Rnote = new Note("r", 348, -71, this);
		Znote = new Note("z", 367, -62, this);
		cUpnote = new Note("cUp", 327, -10, this);
		stickUpnote = new Note("stickUp", 264, -43, this);
		stickDLeftnote = new Note("stickDLeft", 264, -43, this);
		stickDownnote = new Note("stickDown", 264, -43, this);
*/
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
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack(); // not sure what this does, supposedly an optimization
		frame.addKeyListener(game.new AL());
		game.addMouseListener(game.new LA());
		// frame.addMouseListener(game.new AL());
		contentPane.add(scrollPane, BorderLayout.CENTER);
		frame.setJMenuBar(game.createMenuBar());
		game.start();

		// savefile
		try {
			hs = new SaveFile();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	// This method is for the sole purpose of ensuring buttons remain in sync even
	// if the user doesn't hit all of the notes
	public void delayFalling() {

		// FOX WAVEDASH
		if (FoxWavedash.isSelected()) {
			if (YnoteFW.getFalling() == false && RnoteFW.getFalling() == false
					&& stickDLeftnoteFW.getFalling() == false) {
				YnoteFW.setFalling(true);
				RnoteFW.setFalling(true);
				stickDLeftnoteFW.setFalling(true);
				counter++;
			}
		}

		// FOX MULTISHINE
		if (multi.isSelected()) {
			if (BnoteMS.getFalling() == false && XnoteMS.getFalling() == false && stickDownnoteMS.getFalling() == false
					&& stickDownnoteMS2.getFalling() == false && stickDownnoteMS3.getFalling() == false) {
				BnoteMS.setFalling(true);
				XnoteMS.setFalling(true);
				stickDownnoteMS.setFalling(true);
				stickDownnoteMS2.setFalling(true);
				stickDownnoteMS3.setFalling(true);
				counter++;
			}
		}

		// FOX L CANCEL NAIR
		if (nair.isSelected()) {
			if (YnoteN.getFalling() == false && stickDownnoteN.getFalling() == false && AnoteN.getFalling() == false
					&& RnoteN.getFalling() == false) {
				AnoteN.setFalling(true);
				YnoteN.setFalling(true);
				stickDownnoteN.setFalling(true);
				RnoteN.setFalling(true);
				counter++;
			}

		}

	}

	private void tick() // everything in the game that updates AND COLLISION
	// IMPORTANT FOR STICKS, Z VALUE FOR EACH: U: 1, D: 2, L: 3, R: 4, UL: 6, UR: 5,
	// DL: 8, DR: 7
	{
		

		if (state == STATE.START) {

		}

		if (state == STATE.PLAY) {
			// FOX WAVEDASH
			if(HardMode.isSelected() && FoxWavedash.isSelected()) {
				YnoteFW.setFallSpeed(5.5);
				RnoteFW.setFallSpeed(5.5);
				stickDLeftnoteFW.setFallSpeed(5.5);
			}
			if(MediumMode.isSelected() && FoxWavedash.isSelected()) {
				YnoteFW.setFallSpeed(2.75);
				RnoteFW.setFallSpeed(2.75);
				stickDLeftnoteFW.setFallSpeed(2.75);
			}
			if(EasyMode.isSelected() && FoxWavedash.isSelected()) {
				YnoteFW.setFallSpeed(1.375);
				RnoteFW.setFallSpeed(1.375);
				stickDLeftnoteFW.setFallSpeed(1.375);
			}
			if(HardMode.isSelected() && multi.isSelected()) {
				XnoteMS.setFallSpeed(6);
				BnoteMS.setFallSpeed(6);
				stickDownnoteMS.setFallSpeed(6);
				stickDownnoteMS2.setFallSpeed(6);
				stickDownnoteMS3.setFallSpeed(6);
			}
			if(MediumMode.isSelected() && multi.isSelected()) {
				XnoteMS.setFallSpeed(3);
				BnoteMS.setFallSpeed(3);
				stickDownnoteMS.setFallSpeed(3);
				stickDownnoteMS2.setFallSpeed(3);
				stickDownnoteMS3.setFallSpeed(3);
			}
			if(EasyMode.isSelected() && multi.isSelected()) {
				XnoteMS.setFallSpeed(1.5);
				BnoteMS.setFallSpeed(1.5);
				stickDownnoteMS.setFallSpeed(1.5);
				stickDownnoteMS2.setFallSpeed(1.5);
				stickDownnoteMS3.setFallSpeed(1.5);
			}
			if (FoxWavedash.isSelected()) {
               
				// sets highscore for fox wavedash
				highscore = hs.hScore(0);
                System.out.println("Y: "+YnoteFW.getY());
				delayFalling();

				YnoteFW.tick();

				RnoteFW.tick();

				stickDLeftnoteFW.tick();
				
				
				if (Physics.Collision(YnoteFW, ybutton2) && YnoteFW.getY() > 352 && YnoteFW.getY() < 360 && ybutton2.getX() == 339) {
					a = 1;
					YnoteFW.setY(-54);
					PlaySound(cSound);
					YnoteFW.setFalling(false);

				}

				if (Physics.Collision(RnoteFW, rbutton2) && RnoteFW.getY() > 335 && RnoteFW.getY() < 343 && rbutton2.getX() == 348) {
					b = 1;

					RnoteFW.setY(-74);
					PlaySound(aSound);
					RnoteFW.setFalling(false);
				}

				if (Physics.Collision(stickDLeftnoteFW, stickDLeft2) && stickDLeftnoteFW.getY() > 364 && stickDLeftnoteFW.getY() < 371
						&& stickDLeft2.getX() == 264 && stickDLeft2.getZ() == 8) {
					c = 1;
					stickDLeftnoteFW.setY(-76);

					// System.out.println(score);
					stickDLeftnoteFW.setFalling(false);

				}

				//System.out.println("counter: " + counter);

				if (counter == 20)
				{
					
					state = STATE.PRESENTSCORE;
				}

			}

			// MULTISHINE
			if (multi.isSelected()) {
				delayFalling();

				XnoteMS.tick();
				BnoteMS.tick();
				stickDownnoteMS.tick();
				stickDownnoteMS2.tick();
				stickDownnoteMS3.tick();

				if (Physics.Collision(BnoteMS, bbutton2) && BnoteMS.getY() > 371 && BnoteMS.getY() < 378 && bbutton2.getX() == 333) {
				
					a = 1;
					//System.out.println(Bnote.getY());
					BnoteMS.setY(-35);

					BnoteMS.setFalling(false);

				}

				if (Physics.Collision(XnoteMS, xbutton2) && XnoteMS.getY() > 354 && XnoteMS.getY() < 361 && xbutton2.getX() == 359) {
					b = 1;
					XnoteMS.setY(-54);

					XnoteMS.setFalling(false);

				}

				if (Physics.Collision(stickDownnoteMS, stickDown3) && stickDownnoteMS.getY() > 360
						&& stickDown3.getX() == 264) {
					c1 = 1;
					stickDownnoteMS.setY(-33);

					stickDownnoteMS.setFalling(false);

				}
				if (Physics.Collision(stickDownnoteMS2, stickDown3) && stickDownnoteMS2.getY() > 360
						&& stickDown3.getX() == 264) {
					c2 = 1;
					stickDownnoteMS2.setY(-48);

					stickDownnoteMS2.setFalling(false);

				}
				if (Physics.Collision(stickDownnoteMS3, stickDown3) && stickDownnoteMS3.getY() > 360
						&& stickDown3.getX() == 264) {
					c3 = 1;
					stickDownnoteMS3.setY(-63);

					stickDownnoteMS3.setFalling(false);

				}

				if (counter == 20)
					// currentBackground = image;
					state = STATE.PRESENTSCORE;

			}
			if (nair.isSelected()) {
				delayFalling();
				AnoteN.tick();
				YnoteN.tick();
				RnoteN.tick();
				stickDownnoteN.tick();

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

		if (a == 1 && b == 1 && (c == 1 || c1 == 1 && c2 == 1 && c3 == 1)) {
			score++;
			a = 0;
			b = 0;
			c = 0;
			c1 = 0;
			c2 = 0;
			c3 = 0;
		}

		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		if (state == STATE.START) {
			// g.drawImage(title, 303, 200, this);
			g.setFont(new Font("Arial", Font.BOLD, 10));
			g.setColor(Color.WHITE);
			g.drawRect(258, 280, 125, 20);
			g.drawString("PLAY", 305, 294);
			g.setColor(Color.CYAN);
			g.setFont(new Font("Arial", Font.BOLD, 70));
			g.drawString("Melee Tech Trainer", 0, 200);
		}
		if (state == STATE.BACKGROUNDS) {
			g.setColor(Color.CYAN);
			g.drawRect(0, 0, 50, 50);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("ESC", 5, 32);
			g.setFont(new Font("Arial", Font.BOLD, 70));
			g.drawString("Choose a Stage", 55, 85);
			g.setFont(new Font("Arial", Font.BOLD, 70));
			g.drawRect(32, 130, 185, 70);
			g.drawRect(32, 210, 185, 70);
			g.drawRect(227, 130, 185, 70);
			g.drawRect(227, 210, 185, 70);
			g.drawRect(422, 130, 185, 70);
			g.drawRect(422, 210, 185, 70);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.setColor(Color.WHITE);
			g.drawString("Final Destination", 64, 169);
			g.drawString("Battlefield", 281, 169);
			g.drawString("Dreamland", 474, 169);
			g.drawString("Yoshi's Story", 76, 247);
			g.drawString("Fountain of Dreams", 250, 247);
			g.drawString("Pokemon Stadium", 450, 247);

		}

		if (state == STATE.PLAY) {

			g.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), this);
			g.setColor(Color.CYAN);
			g.drawRect(0, 0, 50, 50);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("ESC", 5, 32);
			g.drawImage(controllerr, 220, 350, this);
			g.drawImage(abutton, 347, 367, this);
			g.drawImage(bbutton, 333, 372, this);
			g.drawImage(xbutton, 359, 356, this);
			g.drawImage(ybutton, 339, 353, this);
			g.drawImage(lbutton, 258, 336, this);
			g.drawImage(rbutton, 348, 336, this);
			g.drawImage(startbutton, 302, 375, this);
			g.drawImage(zbutton, 347, 345, this);
			g.drawImage(stick, 264, 364, this);
			g.drawImage(cstick, 327, 397, this); // UHJK
			if (pause == 1) {
				g.drawImage(pauseIcon, 300, 200, this);
			}
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
			stickDown3.render(g);
			// Anote.render(g)'

			// Scoring letters
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("SCORE:" + score, 490, 25);

			if (multi.isSelected()) {
				BnoteMS.render(g);
				XnoteMS.render(g);
				stickDownnoteMS.render(g);
				stickDownnoteMS2.render(g);
				stickDownnoteMS3.render(g);
			}

			// We need to divide the buttons that comprise of each tech into this render, so
			// that they only are on screen when selected
			if (FoxWavedash.isSelected()) {
				YnoteFW.render(g);
				//System.out.println("Y Note IS RENDERED");
				RnoteFW.render(g);
				stickDLeftnoteFW.render(g);
			
				// keeps track of highscore
				if (score > highscore) {
					hs.modifyFile(highscore + " FWD", score + " FWD");

					// recreates the array so the new value can be replaced if it increases by more
					// than one
					try {
						hs = new SaveFile();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				g.drawString(" HIGH SCORE:" + highscore, 399, 50);
			}
			//System.out.println(score);

			if (nair.isSelected()) {
				AnoteN.render(g);
				RnoteN.render(g);
				stickDownnoteN.render(g);
				YnoteN.render(g);
			}

			// Lnote.render(g);
			// Znote.render(g);
			// cUpnote.render(g);
			// stickUpnote.render(g);

			//////////////////////////////////// where we can draw images ^^^^^
		}
		//System.out.println(score);
		if (state == STATE.PRESENTSCORE) {
			counter = 1;
			//System.out.println(score);
			// currentBackground = image;
			g.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), this);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("YOUR SCORE: " + score, 200, 210);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawRect(256, 240, 125, 20);
			g.drawString("RESTART", 282, 255);
			// g.drawString("Press A to restart", 257, 280);
			score = 0;
		}

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

	// This class is used for the ButtonFlash class, enabling communication with the
	// keyboard and the class
	public class LA implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();

			// TODO Auto-generated method stub

			if (e.getButton() == MouseEvent.BUTTON1) {
				if (state == STATE.START) {
					if (mx >= 258 && mx <= 383) {
						if (my >= 280 && my <= 300) {
							state = STATE.BACKGROUNDS;

						}
					}
				}
			}

			if (state == STATE.BACKGROUNDS) {
				if (mx >= 32 && mx <= 217) {
					if (my >= 130 && my <= 200) {
						currentBackground = FDBackground;
						state = STATE.PLAY;

					}
				}

				if (mx >= 0 && mx <= 50) {
					if (my >= 0 && my <= 50) {
						state = STATE.START;
					}
				}
			}
			if (state == STATE.PLAY) {
				if (mx >= 0 && mx <= 50) {
					if (my >= 0 && my <= 50) {
						state = STATE.BACKGROUNDS;
					}
				}
			}

			if (state == STATE.PRESENTSCORE) {
				if (mx >= 256 && mx <= 381) {
					if (my >= 240 && my <= 260) {
						// currentBackground = image;
						state = STATE.PLAY;
					}
				}

			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

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
			stickDLeft2.keyPressed(e);

			// if (e.getKeyCode() == KeyEvent.VK_A && state == STATE.PRESENTSCORE) {
			// state = STATE.PLAY;

			// }
			zbutton2.keyPressed(e);
			stickDLeft2.keyPressed(e);

			stickDown3.keyPressed(e);

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
			stickDown3.keyReleased(e);
		}

	}

	public void setState(STATE state) {
		this.state = state;

	}

	public void setBackground(BufferedImage img) {
		currentBackground = img;

	}
	// plays the sound

	public void setA(int x) {
		a = x;

	}

	public void setB(int x) {
		b = x;

	}

	public void setC(int x) {
		c = x;

	}

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
		JMenu menuFox;
		JMenu difficulties;

		// Create the menu bar.

		menuBar = new JMenuBar();

		// Menus
		menuFox = new JMenu("    Fox    ");
		difficulties = new JMenu("    Difficulties    ");
		menuBar.add(difficulties);
		menuBar.add(menuFox);

		// menu.addSeparator();

		ButtonGroup difficultygroup = new ButtonGroup();
		ButtonGroup foxgroup = new ButtonGroup();

		EasyMode = new JRadioButtonMenuItem("Beginner (.25x speed)");
		MediumMode = new JRadioButtonMenuItem("Intermediate (.5x speed)");
		HardMode = new JRadioButtonMenuItem("Advanced (1x speed)");
		difficultygroup.add(EasyMode);
		difficultygroup.add(MediumMode);
		difficultygroup.add(HardMode);
		difficulties.add(EasyMode);
		difficulties.add(MediumMode);
		difficulties.add(HardMode);

		FoxWavedash = new JRadioButtonMenuItem("Wavedash");
		FoxWavedash.setSelected(true);
		foxgroup.add(FoxWavedash);
		menuFox.add(FoxWavedash);
		multi = new JRadioButtonMenuItem("Multishine");
		foxgroup.add(multi);
		menuFox.add(multi);

		nair = new JRadioButtonMenuItem("Neutral Air");
		foxgroup.add(nair);
		menuFox.add(nair);

		shortHopDL = new JRadioButtonMenuItem("SH Double Laser");
		foxgroup.add(shortHopDL);
		menuFox.add(shortHopDL);

		ledgeDash = new JRadioButtonMenuItem("Ledgedash");
		foxgroup.add(ledgeDash);
		menuFox.add(ledgeDash);
		MarthWavedash = new JRadioButtonMenuItem("Wavedash");

		return menuBar;
	}

}