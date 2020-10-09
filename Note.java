import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.game.src.main.classes.EntityA;
import java.awt.event.KeyEvent;

public class Note extends GameObject implements EntityA {

	private String str;
	GameWindow g;
	BufferedImage A;
	BufferedImage B;
	BufferedImage X;
	BufferedImage Y;
	BufferedImage L;
	BufferedImage R;
	BufferedImage Z;
	BufferedImage cUp;
	BufferedImage stickUp;
	BufferedImage stickDLeft;
	BufferedImage stickDown;
	private boolean falling = true;
	private int initialY;

	public Note(String str, int x, int y, GameWindow g) {

		super(x, y);
		initialY = y;
		this.g = g;
		this.str = str;
		// this.type = type;
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		A = ss.grabImage(1, 1, 32, 32);
		B = ss.grabImage(2, 1, 32, 32);
		X = ss.grabImage(4, 1, 32, 32);
		Y = ss.grabImage(3, 1, 32, 32);
		L = ss.grabImage(5, 1, 32, 32);
		R = ss.grabImage(6, 1, 32, 32);
		Z = ss.grabImage(7, 1, 32, 32);
		cUp = ss.grabImage(6, 3, 32, 32);
		stickUp = ss.grabImage(5, 2, 32, 32);
		stickDLeft = ss.grabImage(1, 2, 32, 32);
		stickDown = ss.grabImage(2, 2, 32, 32);
	}

	public void tick() // update method
	{

		if (falling == true) {
			if (y < 461)
				y = y + 3;
		}
		if (y > 460) {
			falling = false;
			y = initialY;

			// y = y + 3;
		}
		System.out.println("y = " + y);

	}
	// note is entity a, checks to see if collision is true for colliding with a
	// buttonflash

	public void render(Graphics g) // draws out image
	{

		if (x == 327 && str == "a") {

			g.drawImage(A, 327, (int) y, null);
		}
		if (x == 313 && str == "b") {
			g.drawImage(B, 313, (int) y, null);
		}
		if (x == 339 && str == "x") {
			g.drawImage(X, 339, (int) y, null);
		}
		if (x == 319 && str == "y") {
			g.drawImage(Y, 319, (int) y, null);
		}
		if (x == 238 && str == "l") {
			g.drawImage(L, 238, (int) y, null);
		}
		if (x == 328 && str == "r") {
			g.drawImage(R, 328, (int) y, null);
		}
		if (x == 327 && str == "z") {
			g.drawImage(Z, 327, (int) y, null);
		}
		if (x == 307 && str == "cUp") {
			g.drawImage(cUp, 307, (int) y, null);
		}
		if (x == 244 && str == "stickUp") {
			g.drawImage(stickUp, 244, (int) y, null);
		}
		if (x == 244 && str == "stickDLeft") {
			g.drawImage(stickDLeft, 244, (int) y, null);
		}
		if (x == 244 && str == "stickDown") {
			g.drawImage(stickDown, 244, (int) y, null);
		}
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

	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public boolean getFalling() {
		return falling;
	}

	public void setFalling(boolean x) {
		falling = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
