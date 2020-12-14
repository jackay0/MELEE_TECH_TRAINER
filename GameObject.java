import java.awt.Rectangle;

//collision pseudo: think of drawing a rectangle hit-box around the buttonflash and notes using rectangle class
//mapping a rectangle that we cannot see, if rectangles collide, indicate in console
//.intersects method will return a true or false value that show when the rectangles touch
public class GameObject {

	public double x;
	public double y;
	
	
	public GameObject(double x, double y) // everything that extends this class will utilize the same variables, basically anything being manipulated on screen
	{
		this.x = x;
		this.y = y;
		
	}
	
	public Rectangle getBounds(int width, int height) {
		
		return new Rectangle((int) x, (int) y, width , height);
	}

	
	

}
