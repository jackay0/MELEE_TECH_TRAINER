
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
	// 320 x 240 pixels
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Melee Tech Trainer";

	private boolean running = false;
	private Thread thread;

	// sound files
	private File pp = new File("/C://Users//0001081009//workspace//melee//src//pp.wav");

	private int pause;
	private int score = 0;
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
	JRadioButtonMenuItem start;
	JRadioButtonMenuItem stage;
	
	//difficulties
	JRadioButtonMenuItem EasyMode;
	JRadioButtonMenuItem MediumMode;
	JRadioButtonMenuItem HardMode;

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
			FDbackground = loader.LoadImage("/FD.png");
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

		Anote = new Note("a", 347, -40, this);
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
				counter++;
			}
		}
		// FOX MULTISHINE
		if (multi.isSelected()) {
			if (Bnote.getFalling() == false && Xnote.getFalling() == false && stickDownnote.getFalling() == false) {
				Bnote.setFalling(true);
				Xnote.setFalling(true);
				stickDownnote.setFalling(true);
				counter++;
			}
		}
	}

	private void tick() // everything in the game that updates AND COLLISION
	// IMPORTANT FOR STICKS, Z VALUE FOR EACH: U: 1, D: 2, L: 3, R: 4, UL: 6, UR: 5,
	// DL: 8, DR: 7
	{
		// System.out.println("WIDTH: " + WIDTH );

		if (state == STATE.START) {

		}

		if (state == STATE.PLAY) {
			// FOX WAVEDASH

			if (FoxWavedash.isSelected()) {

				delayFalling();

				Ynote.tick();

				Rnote.tick();

				stickDLeftnote.tick();

				if (Physics.Collision(Ynote, ybutton2) && Ynote.getY() > 353 && ybutton2.getX() == 339) {
					a = 1;
					Ynote.setY(-54);
					PlaySound(pp);
					// System.out.println(score);
					Ynote.setFalling(false);

				}

				if (Physics.Collision(Rnote, rbutton2) && Rnote.getY() > 336 && rbutton2.getX() == 348) {
					b = 1;

					Rnote.setY(-71);
					PlaySound(pp);
					Rnote.setFalling(false);
				}

				if (Physics.Collision(stickDLeftnote, stickDLeft2) && stickDLeftnote.getY() > 364
						&& stickDLeft2.getX() == 264 && stickDLeft2.getZ() == 8) {
					c = 1;
					stickDLeftnote.setY(-43);
					PlaySound(pp);
					// System.out.println(score);
					stickDLeftnote.setFalling(false);

				}
				System.out.println("counter: " + counter);

				if (counter == 20)
					state = STATE.PRESENTSCORE;

			}

			// MULTISHINE
			if (multi.isSelected()) {
				delayFalling();

				Xnote.tick();

				Bnote.tick();

				stickDownnote.tick();

				if (Physics.Collision(Bnote, bbutton2) && Bnote.getY() > 371 && bbutton2.getX() == 333) {
					a = a + 1;
					Bnote.setY(-35);
					PlaySound(pp);
					Bnote.setFalling(false);

				}

				if (Physics.Collision(Xnote, xbutton2) && Xnote.getY() > 355 && xbutton2.getX() == 359) {
					a = a + 1;
					Xnote.setY(-54);
					PlaySound(pp);
					Xnote.setFalling(false);

				}

				if (Physics.Collision(stickDownnote, stickDown2) && stickDownnote.getY() > 360
						&& stickDown2.getX() == 264) {
					a = a + 1;
					stickDownnote.setY(-43);
					PlaySound(pp);
					stickDownnote.setFalling(false);

				}

				if (counter == 20)
					//currentBackground = image;
					state = STATE.PRESENTSCORE;

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

		if (a == 1 && b == 1 && c == 1) {
			score++;
			a = 0;
			b = 0;
			c = 0;
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
			g.drawImage(cstick, 347, 397, this); // UHJK
			if (pause == 1) {
				g.drawImage(pauseIcon, 300, 200, this);
			}

			// Scoring letters
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("SCORE:" + score, 500, 25);

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
			// Anote.render(g);

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

			// Lnote.render(g);
			// Znote.render(g);
			// cUpnote.render(g);
			// stickUpnote.render(g);

			//////////////////////////////////// where we can draw images ^^^^^
		}

		if (state == STATE.PRESENTSCORE) {
			counter = 1;
			//currentBackground = image;
			g.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), this);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.WHITE);
			g.drawString("YOUR SCORE: " + score, 200, 210);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawRect(256, 240, 125, 20);
			g.drawString("RESTART", 282, 255);
			// g.drawString("Press A to restart", 257, 280);
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

	public double getDistance() {

		return Anote.getY() - ButtonFlash.getY();
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
				
			if(state == STATE.PRESENTSCORE) {
				if (mx >= 256 && mx <= 381) {
					if (my >= 240 && my <= 260) {
						//currentBackground = image;
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
	public void setState(STATE state) {
		this.state = state;

	}

	public void setBackground(BufferedImage img) {
		currentBackground = img;

	}

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
		JMenu fox;
		JMenu difficulties;
		
		// Create the menu bar.

		menuBar = new JMenuBar();

		// Menus
		fox = new JMenu("    Fox    ");
        difficulties = new JMenu("    Difficulties    ");
        menuBar.add(difficulties);
        menuBar.add(fox);
		

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
		
		FoxWavedash = new JRadioButtonMenuItem("Fox Wavedash");
		FoxWavedash.setSelected(true);
		foxgroup.add(FoxWavedash);
		fox.add(FoxWavedash);
		multi = new JRadioButtonMenuItem("Fox Multishine");
		foxgroup.add(multi);
		fox.add(multi);

		MarthWavedash = new JRadioButtonMenuItem("Marth Wavedash");
		
		return menuBar;
	}

}