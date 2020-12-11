import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import com.game.src.main.classes.EntityB;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;


public class ButtonFlash extends GameObject implements EntityB { // this is not to be confused with the NOTE class which
																	// will be the moving notes that fall on screen
	// this is not to be confused with the NOTE class which will be the moving notes
	// that fall on screen
	private boolean pressed = false;
	private int z;
	GameWindow g;
	private BufferedImage abutton2; // sprite for white a button
	private BufferedImage bbutton2; // sprite for b button
	private BufferedImage xbutton2; // sprite for x button
	private BufferedImage ybutton2; // sprite for y button
	private BufferedImage lbutton2;
	private BufferedImage rbutton2;
	private BufferedImage startbutton2;
	private BufferedImage zbutton2;
	private BufferedImage stickUp2;
	private BufferedImage stickDown2;
	private BufferedImage stickLeft2;
	private BufferedImage stickRight2;
	private BufferedImage stickURight2;
	private BufferedImage stickULeft2;
	private BufferedImage stickDRight2;
	private BufferedImage stickDLeft2;
	private BufferedImage cUp2;
	private BufferedImage cDown2;
	private BufferedImage cLeft2;
	private BufferedImage cRight2;
	private BufferedImage cURight2;
	private BufferedImage cULeft2;
	private BufferedImage cDRight2;
	private BufferedImage cDLeft2;

	public ButtonFlash(double x, double y, GameWindow g) {
		super(x, y);
		this.g = g;
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		abutton2 = ss.grabImage(3, 4, 32, 32);
		bbutton2 = ss.grabImage(4, 4, 32, 32);
		xbutton2 = ss.grabImage(6, 4, 32, 32);
		ybutton2 = ss.grabImage(5, 4, 32, 32);
		lbutton2 = ss.grabImage(1, 5, 32, 32);
		rbutton2 = ss.grabImage(2, 5, 32, 32);
		startbutton2 = ss.grabImage(8, 4, 32, 32);
		zbutton2 = ss.grabImage(7, 4, 32, 32);
		stickUp2 = ss.grabImage(1, 6, 32, 32);
		stickDown2 = ss.grabImage(5, 5, 32, 32);
		stickLeft2 = ss.grabImage(3, 5, 32, 32);
		stickRight2 = ss.grabImage(7, 5, 32, 32);
		stickURight2 = ss.grabImage(8, 5, 32, 32);
		stickULeft2 = ss.grabImage(2, 6, 32, 32);
		stickDRight2 = ss.grabImage(6, 5, 32, 32);
		stickDLeft2 = ss.grabImage(4, 5, 32, 32);
		// c stick
		cDown2 = ss.grabImage(4, 6, 32, 32);
		cUp2 = ss.grabImage(7, 6, 32, 32);
		cLeft2 = ss.grabImage(1, 7, 32, 32);
		cRight2 = ss.grabImage(2, 7, 32, 32);
		// c stick diagonals
		cURight2 = ss.grabImage(8, 6, 32, 32);
		cULeft2 = ss.grabImage(6, 6, 32, 32);
		cDLeft2 = ss.grabImage(3, 6, 32, 32);
		cDRight2 = ss.grabImage(5, 6, 32, 32);

	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public void setY(double y) {
		// TODO Auto-generated method stub
	   this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}

	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public int getZ() {
		return z;
	}

	@Override
	public void tick() {
		//System.out.println(x);
		
		//while(pressed == true) {
		//	x=359;
		 //   y=356;
		//}
	}

	public boolean IsPressed(Component.Identifier identifier) {
		//System.out.println(identifier);

		if (identifier.equals(Component.Identifier.Button._0)) {
			//System.out.println(x);
			//x = 359;
			//y = 356;
			return true;
			//setPressed(true);
			
		}
		return false;
		
		//setPressed(false);
		
	}
	

	@Override
	public void render(Graphics g) {
		
		if (x == 347 && y == 367) {
			g.drawImage(abutton2, (int) x, (int) y, null);
		}
		if (x == 333 && y == 372) {
			g.drawImage(bbutton2, (int) x, (int) y, null);
		}
		
		if (x == 359 && y == 356) {
			g.drawImage(xbutton2, (int) x, (int) y, null);
		}
		
		if (x == 339 && y == 353) {
			g.drawImage(ybutton2, (int) x, (int) y, null);
		}
		if (x == 258 && y == 336) {
			g.drawImage(lbutton2, (int) x, (int) y, null);
		}
		if (x == 302 && y == 375) {
			g.drawImage(startbutton2, (int) x, (int) y, null);
		}
		if (x == 348 && y == 336) {
			g.drawImage(rbutton2, (int) x, (int) y, null);
		}
		if (x == 347 && y == 345) {
			g.drawImage(zbutton2, (int) x, (int) y, null);
		}
		if (x == 264 && y == 364 && z == 1) {
			g.drawImage(stickUp2, (int) x, (int) y, null);
		}
		if (x == 264 && y == 364 && z == 2) {
			g.drawImage(stickDown2, (int) x, (int) y, null);
		}
		if (x == 264 && y == 364 && z == 3) {
			g.drawImage(stickLeft2, (int) x, (int) y, null);
		}

		if (x == 264 && y == 364 && z == 4) {
			g.drawImage(stickRight2, (int) x, (int) y, null);
		}

		if (x == 264 && y == 364 && z == 5) {
			g.drawImage(stickURight2, (int) x, (int) y, null);
		}

		if (x == 264 && y == 364 && z == 6) {
			g.drawImage(stickULeft2, (int) x, (int) y, null);
		}

		if (x == 264 && y == 364 && z == 7) {
			g.drawImage(stickDRight2, (int) x, (int) y, null);
		}

		if (x == 264 && y == 364 && z == 8) {
			g.drawImage(stickDLeft2, (int) x, (int) y, null);
		}

		if (x == 327 && y == 397 && z == 1) {
			g.drawImage(cUp2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 2) {
			g.drawImage(cLeft2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 3) {
			g.drawImage(cDown2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 4) {
			g.drawImage(cRight2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 5) {
			g.drawImage(cURight2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 6) {
			g.drawImage(cULeft2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 7) {
			g.drawImage(cDRight2, (int) x, (int) y, null);
		}
		if (x == 327 && y == 397 && z == 8) {
			g.drawImage(cDLeft2, (int) x, (int) y, null);
		}

	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
}