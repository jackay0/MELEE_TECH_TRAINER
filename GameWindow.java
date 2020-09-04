package boyandahalf;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JPanel {
   JFrame window = new JFrame();
	GameWindow(){
		window.add(this);
		window.setVisible(true);
		window.setSize(1280,720);
		window.setVisible(true);
		
	}
	public void paint(Graphics g) {
		ImageIcon background = new ImageIcon("images//White Background.png");
		g.drawImage(background.getImage(),0,0,null);
	}
	
}
