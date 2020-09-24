import java.awt.event.KeyEvent;

public class Input {
	GameWindow g;
	private Note anote = g.GetANote();
	
	public Input(GameWindow g) {
		this.g = g;	
	}


	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_S) {
			anote.setFalling(false);
		}

	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_S) {
			anote.setFalling(true);
		}

	}
}
