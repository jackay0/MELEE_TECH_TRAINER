import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Note {

private double x;
private double y;
private String type;
GameWindow g;
BufferedImage a;





public Note(int x, int y, GameWindow g) 
{
	
	this.x = x;
	this.g = g;
	this.y = y;
	//this.type = type;
	SpriteSheet z = new SpriteSheet(g.getSpriteSheet());
	a = z.grabImage(1, 1, 32, 32);

}



public void tick() //update method
{
	y++;

}



public void render(Graphics g)  //draws out image
{
	g.drawImage(a, (int) x, (int) y, null);


}




}
