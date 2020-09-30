import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.game.src.main.classes.EntityA;
import java.awt.event.KeyEvent;

public class Note extends GameObject implements EntityA {

	private String type;
	GameWindow g;
	BufferedImage A;
	BufferedImage B;
	BufferedImage X;
	BufferedImage Y;
	BufferedImage L;
	BufferedImage R;
	BufferedImage Z;
	BufferedImage stickUp;
	private boolean falling = true;

	public Note(int x, int y, GameWindow g) {

		super(x, y);
		this.g = g;

		// this.type = type;
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		A = ss.grabImage(1, 1, 32, 32);
		B = ss.grabImage(2, 1, 32, 32);
		X = ss.grabImage(4, 1, 32, 32);
		Y = ss.grabImage(3, 1, 32, 32);
		L = ss.grabImage(5, 1, 32, 32);
		R = ss.grabImage(6, 1, 32, 32);
		Z = ss.grabImage(7, 1, 32, 32);
		stickUp = ss.grabImage(5, 2, 32, 32);
	}

	public void tick() // update method
	{
	 if (falling == true) {
			if (y < 460)
				y=y+3;
			else {
				y = 0;
				y=y+3;  
			}
		}
		// note is entity a, checks to see if collision is true for colliding with a
		// buttonflash
	}

	public void render(Graphics g) // draws out image
	{
		g.drawImage(A, 327, (int) y, null);
		g.drawImage(B, 313, (int) y, null);
		g.drawImage(X, 339, (int) y, null);
		g.drawImage(Y, 319, (int) y, null);
		g.drawImage(L, 238, (int) y, null);
		g.drawImage(R, 327, (int) y, null);
		g.drawImage(Z, 327, (int) y, null);
		g.drawImage(stickUp, 244, (int) y, null);
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

		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (falling)
				falling = false;
			else if (!falling)
				falling = true;
		}

	}

}
