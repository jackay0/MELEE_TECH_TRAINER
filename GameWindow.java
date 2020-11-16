
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	private File eSound = new File("./Notes/E.wav");
	private File gsSound = new File("./Notes/Gsharp.wav");
	
	
	
	
	
	private int pause;
	private int score = 0;
	private int beginnerScores[] = new int[10];
	private int intermediateScores[] = new int[10];
	private int advancedScores[] = new int[10];
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage controller = null;
	private BufferedImage pauseSheet = null;

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
	
	//EXTRAS
	private BufferedImage controllerr;
	private BufferedImage pauseIcon;
	private BufferedImage title;

	// vars dealing specifically with scoring
	private int a = 0;
	private int b = 0;
	private int c = 0;
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
	//private ButtonFlash stickDown2;
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
	
	//ButtonFlashSticks
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

	
	

	
	// Fox Tech 
	//{
	JRadioButtonMenuItem FoxWavedash;
	JRadioButtonMenuItem multi;
	JRadioButtonMenuItem nair;
	JRadioButtonMenuItem FoxWavedashOOS;
	JRadioButtonMenuItem shortHopDL;
	JRadioButtonMenuItem ledgeDash;
	
	 	//WAVEDASH
	
		private Note YnoteFW;
		private Note RnoteFW;
		private Note stickDLeftnoteFW;
	

		//MULTISHINE
		
		//VARS FOR FALLING NOTES
	
		//NAIR
		private Note AnoteN;
		private Note RnoteN;
		private Note stickDownnoteN;
		private Note YnoteN;
	
		//Ledgedash
		private Note stickDRightnoteLD;
		private Note YnoteLD;
		private Note stickleftnoteLD;
		private Note RnoteLD;
	
		//Short hop double laser
		private Note BnoteSHDL;
		
		
		
		//}
	
		
		private enum STATE{
		START,
		PLAY,
		MENU
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
		//stickDown2 = new ButtonFlash(-100, -100, this);
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

		
		//ButtonFlashSticks
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
		
		//EXTRAS
		controllerr = gcc.grabImage(1, 1, 200, 100);
		pauseIcon = p.grabImage(1,1,32,32);
		title = t.grabImage(1, 1, 32,32);
		
		
		
		//INITIALIZES notes for each tech, varies in position depending on the technique
		
		
		
		//WAVEDASH
		//at frame four of the jump is the airdodge, aka 4/60 of a second, aka 1/15 of a second
		//the note is moved BY the tick method, which y=y+1 every 1/60th of a second
		//therefore, y of the downleft and r is 4 y places away from the y?
		//this doesn't necessarily work because the hitboxes are 32 x 32, half of it is 16, add 4?20?
		RnoteFW = new Note("r", 328, -74, this);
		YnoteFW = new Note("y", 319, -54, this);
		stickDLeftnoteFW = new Note("stickDLeft", 244, -74, this);
		
		
		
		//MULTISHINE
		//here is where the notes are specified in positions
		
		
		
		//NAIR
		AnoteN = new Note("a", 327, -40, this);
		stickDownnoteN = new Note("stickDown",244,-30,this);
		RnoteN = new Note("r", 328, -71, this);
		YnoteN = new Note("y", 319, -10, this);
		
		//Ledgedash
		RnoteLD = new Note("r", 328, -71, this);
		YnoteLD = new Note("y", 319, -54, this);
		stickDRightnoteLD = new Note("stickDRight", 244, -43, this);
		stickleftnoteLD = new Note("stickLeft",244,-43,this);
		
		//Short hop double laser
		
		
		
		
		
		
		
		//Anote = new Note("a", 327, aY, this); //-40
		//Bnote = new Note("b", 313, bY, this); //-35
		//Xnote = new Note("x", 339, xY, this); //-51
		//Ynote = new Note("y", 319, -54, this); //-54
		//Lnote = new Note("l", 238, lY, this); //-71
		//Rnote = new Note("r", 328, -71, this); //-71
		//Znote = new Note("z", 327, zY, this); //-62
		//cUpnote = new Note("cUp", 307, -10, this);
		//stickUpnote = new Note("stickUp", 244, -43, this);
		//stickDLeftnote = new Note("stickDLeft", 244, -43, this); //-43
		//stickDownnote = new Note("stickDown", 244, sdY, this);   //-43
		
	
	
	
	
	
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

		//savefile
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

	
	
	
	//This method is for the sole purpose of ensuring buttons remain in sync even if the user doesn't hit all of the notes
	public void delayFalling() {
		
		// FOX WAVEDASH
		if(FoxWavedash.isSelected()) {
			if (YnoteFW.getFalling() == false && RnoteFW.getFalling() == false && stickDLeftnoteFW.getFalling() == false) {
				YnoteFW.setFalling(true);
				RnoteFW.setFalling(true);
				stickDLeftnoteFW.setFalling(true);
				
			}
		}
		
		
		
		// FOX MULTISHINE
		//this is so they get reset at the top each time
	
	
	
		//FOX L CANCEL NAIR
		if(nair.isSelected()) {
			if(YnoteN.getFalling() == false && stickDownnoteN.getFalling() == false && AnoteN.getFalling() == false && RnoteN.getFalling() == false)
			{
				AnoteN.setFalling(true);
				YnoteN.setFalling(true);
				stickDownnoteN.setFalling(true);
				RnoteN.setFalling(true);
			}
		}
	
	
	}
	
	private void tick() // everything in the game that updates AND COLLISION
	//IMPORTANT FOR STICKS, Z VALUE FOR EACH: U: 1, D: 2, L: 3, R: 4, UL: 6, UR: 5, DL: 8, DR: 7
	{
		
		
		
		
		if(state == STATE.START) {
			
			
			
			
		}
			
			
			
		if(state == STATE.PLAY)
		{
			
			
			// FOX WAVEDASH

			
		if (FoxWavedash.isSelected()) {
			score = advancedScores[0];
			
			//sets highscore for fox wavedash
			
			highscore = hs.hScore(0);
			
			delayFalling();

			YnoteFW.tick();

			RnoteFW.tick();

			stickDLeftnoteFW.tick();

			
			if (Physics.Collision(YnoteFW, ybutton2) && YnoteFW.getY() > 353 && ybutton2.getX() == 319) {
				a = 1;
				YnoteFW.setY(-54);
				PlaySound(cSound);
				YnoteFW.setFalling(false);

			}

			if (Physics.Collision(RnoteFW, rbutton2) && RnoteFW.getY() > 336 && rbutton2.getX() == 328) {
				b = 1;

				RnoteFW.setY(-71);
				PlaySound(aSound);
				RnoteFW.setFalling(false);
			}

			if (Physics.Collision(stickDLeftnoteFW, stickDLeft2) && stickDLeftnoteFW.getY() > 364
					&& stickDLeft2.getX() == 244 && stickDLeft2.getZ()==8) {
				c = 1;
				stickDLeftnoteFW.setY(-74);
				PlaySound(eSound);
				// System.out.println(score);
				stickDLeftnoteFW.setFalling(false);

			}

		}

		// MULTISHINE
			//alot of stuff dealing with collision
		
			
		
		if(nair.isSelected()) {
			highscore = hs.hScore(2);
			delayFalling();
			AnoteN.tick();
			YnoteN.tick();
			RnoteN.tick();
			stickDownnoteN.tick();
		
		
			if (Physics.Collision(RnoteN, rbutton2) && RnoteN.getY() > 336 && rbutton2.getX() == 328) {
				b = 1;

				RnoteN.setY(-71);
				PlaySound(aSound);
				RnoteN.setFalling(false);
			}
		
			if (Physics.Collision(YnoteN, ybutton2) && YnoteN.getY() > 353 && ybutton2.getX() == 319) {
				a = 1;
				YnoteN.setY(-10);
				PlaySound(cSound);
				YnoteN.setFalling(false);

			}
		
		
			if (Physics.Collision(stickDownnoteN, stickDown3) && stickDownnoteN.getY() > 360
					&& stickDown3.getX() == 244) {
				c1= 1;
				stickDownnoteN.setY(-30);
				
				stickDownnoteN.setFalling(false);
			}
		
			if (Physics.Collision(AnoteN, abutton2) && AnoteN.getY() > 336 && abutton2.getX() == 327) {
				b = 1;

				AnoteN.setY(-40);
				PlaySound(gsSound);
				AnoteN.setFalling(false);
			}
		
		}
		
		if(shortHopDL.isSelected()) {
			//highscore = hs.hScore(3);
		}
		
		if(ledgeDash.isSelected()) {
			//highscore = hs.hScore(3);
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

		if (a == 1 && b == 1 && (c1== 1&&c2== 1&&c3== 1||c ==1 )) {
			
			a = 0;
			b = 0;
			c = 0;
			c1=0;
			c2=0;
			c3=0;
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		if(state == STATE.START) 
		{
			g.drawImage(title,290,200,this);
			g.setFont(new Font("Arial", Font.BOLD, 10));
			g.setColor(Color.WHITE);
			g.drawString("Press A" , 285, 280);
		
		}
		
	
		
		
		
	if(state == STATE.PLAY)	
	{
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.WHITE);
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
		
		
		if(pause==1) 
		{
			g.drawImage(pauseIcon, 290, 200, this);
		}
		
		//Scoring letters
		
		g.drawString("SCORE:" + score, 20, 25);
		
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
		
		
		// Anote.render(g);

		//MULTISHINE
		//here I would do stuff dealing with scoring
			
		
		// We need to divide the buttons that comprise of each tech into this render, so
		// that they only are on screen when selected
		if (FoxWavedash.isSelected()) {
			
			//keeps track of highscore
			
			//g.drawString(" HIGH SCORE:" + highscore, 12, 50);
			YnoteFW.render(g);
			RnoteFW.render(g);
			stickDLeftnoteFW.render(g);
			if(score > highscore && hs.current(0).contains("FWD"))
			{
				hs.modifyFile(""+ highscore + " FWD" ,  ""+ score + " FWD" );
				
				//recreates the array so the new value can be replaced if it increases by more than one
				try {
					hs = new SaveFile();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			g.drawString(" HIGH SCORE:" + highscore, 12, 50);
		}

		if(nair.isSelected()) {
			AnoteN.render(g);
			RnoteN.render(g);
			stickDownnoteN.render(g);
			YnoteN.render(g);
		}

		//////////////////////////////////// where we can draw images ^^^^^
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
			if(e.getKeyCode()== KeyEvent.VK_A && state == STATE.START) {
				state = STATE.PLAY;
			}
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

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Menus
		menuFox = new JMenu("Fox    	");

		menuBar.add(menuFox);

		// menu.addSeparator();

		ButtonGroup group = new ButtonGroup();

		FoxWavedash = new JRadioButtonMenuItem("Wavedash");

		FoxWavedash.setSelected(true);
		group.add(FoxWavedash);
		menuFox.add(FoxWavedash);

		

		multi = new JRadioButtonMenuItem("Multishine");
		group.add(multi);
		menuFox.add(multi);

		
		nair = new JRadioButtonMenuItem("Neutral Air");
		group.add(nair);
		menuFox.add(nair);
		
		
		shortHopDL = new JRadioButtonMenuItem("SH Double Laser");
		group.add(shortHopDL);
		menuFox.add(shortHopDL);
		
		ledgeDash = new JRadioButtonMenuItem("Ledgedash");
		group.add(ledgeDash);
		menuFox.add(ledgeDash);
		
		return menuBar;
	}

}