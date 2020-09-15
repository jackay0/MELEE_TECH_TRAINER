import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ButtonFlash {                    //this is not to be confused with the NOTE class which will be the moving notes that fall on screen

    private double x;
    private double y;
    GameWindow g;
    private BufferedImage abutton2; //sprite for white a button
    private BufferedImage bbutton2; //sprite for b button
    private BufferedImage xbutton2; //sprite for x button
    private BufferedImage ybutton2; //sprite for y button
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


    public ButtonFlash(double x, double y, GameWindow g) {
        this.x = x;
        this.y = y;
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

    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            x = 400;
            y = 400;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            x = 360;
            y = 420;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            x = 440;
            y = 385;
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            x = 385;
            y = 370;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            x = 250;
            y = 340;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            x = 405;
            y = 340;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            x = 300;
            y = 420;
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            x = 390;
            y = 350;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            x = 245;
            y = 390;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            x = 246;
            y = 390;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x = 244;
            y = 390;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x = 245;
            y = 391;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            x = 245;
            y = 389;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            x = 246;
            y = 391;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            x = 244;
            y = 389;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            x = 247;
            y = 390;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            //System.out.println("a");
            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {

            x = -100;
            y = -100;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {

            x = -100;
            y = -100;
        }
    }

    public void tick() //update method
    {


    }


    public void render(Graphics g)  //draws out image
    {
        if (x == 400 && y == 400) {
            g.drawImage(abutton2, (int) x, (int) y, null);
        }
        if (x == 360 && y == 420) {
            g.drawImage(bbutton2, (int) x, (int) y, null);
        }
        if (x == 440 && y == 385) {
            g.drawImage(xbutton2, (int) x, (int) y, null);
        }
        if (x == 385 && y == 370) {
            g.drawImage(ybutton2, (int) x, (int) y, null);
        }
        if (x == 250 && y == 340) {
            g.drawImage(lbutton2, (int) x, (int) y, null);
        }
        if (x == 405 && y == 340) {
            g.drawImage(rbutton2, (int) x, (int) y, null);
        }
        if (x == 245 && y == 390) {
            g.drawImage(stickUp2, (int) x, (int) y, null);
        }
        if (x == 246 && y == 390) {
            g.drawImage(stickDown2, (int) x, (int) y, null);
        }
        if (x == 244 && y == 390) {
            g.drawImage(stickLeft2, (int) x, (int) y, null);
        }

        if (x == 245 && y == 391) {
            g.drawImage(stickRight2, (int) x, (int) y, null);
        }

        if (x == 245 && y == 389) {
            g.drawImage(stickURight2, (int) x, (int) y, null);
        }

        if (x == 246 && y == 391) {
            g.drawImage(stickULeft2, (int) x, (int) y, null);
        }

        if (x == 244 && y == 389) {
            g.drawImage(stickDRight2, (int) x, (int) y, null);
        }

        if (x == 247 && y == 390) {
            g.drawImage(stickDLeft2, (int) x, (int) y, null);
        }

        if (x == 390 && y == 350) {
            g.drawImage(zbutton2, (int) x, (int) y, null);
        }


    }


}


