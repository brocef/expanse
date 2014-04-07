package core.graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseHandler implements MouseListener, MouseWheelListener {
	private Viewport view;
	public MouseHandler(Viewport view) {
		this.view = view;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		view.processMouseClick(evt.getX(), evt.getY());
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

}
