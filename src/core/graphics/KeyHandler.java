package core.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	private Viewport view;
	
	public KeyHandler(Viewport view) {
		this.view = view;
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			view.pan(0, -5);
		} else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			view.pan(0, 5);
		} else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			view.pan(-5, 0);
		} else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			view.pan(5, 0);
		}
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		
	}

}
