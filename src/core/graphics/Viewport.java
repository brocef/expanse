package core.graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import core.Tile;

public class Viewport {
	private JFrame viewport;
	private PaintPanel painter;
	private KeyHandler keyz;
	private MouseHandler mousez;
	private InfoDisplay info;
	private int centerx, centery;
	private Tile[][] map;
	
	public Viewport(Tile[][] map) {
		this.map = map;
		this.keyz = new KeyHandler(this);
		this.mousez = new MouseHandler(this);
		
		info = new InfoDisplay();
		viewport = new JFrame();
		viewport.setSize(700, 500);
		painter = new PaintPanel(this);
		viewport.add(painter);
		painter.setPreferredSize(viewport.getSize());
		viewport.setVisible(true);
		viewport.pack();
		viewport.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		viewport.addKeyListener(keyz);
		painter.addMouseListener(mousez);
		painter.addMouseWheelListener(mousez);
		
		centerx = 250;
		centery = 250;
		painter.changeCenter(centerx, centery);
	}
	
	public boolean isTileSelected(Tile t) {
		return info.isSelected(t);
	}
	
	protected InfoDisplay getInfoDisplay() {
		return this.info;
	}
	
	public Tile[][] getMapTiles() {
		return this.map;
	}
	
	public void pan(int newx, int newy) {
		centerx += newx;
		centery += newy;
		painter.changeCenter(centerx, centery);
		viewport.repaint();
	}
	
	public void processMouseClick(int x, int y) {
		//System.out.println("Mouse clicked "+x+", "+y);
		if (x < painter.getWidth()-200) {
			x -= (painter.getWidth()-200)/2-centerx;
			y -= (painter.getHeight())/2-centery;
			for (Tile[] tiles:map) {
				for (Tile t:tiles) {
					if (t == null) continue;
					if (t.getPolygon().contains(x, y)) {
						info.selectTile(t);
						viewport.repaint();
						return;
					}
				}
			}
			info.selectTile(null);
			viewport.repaint();
		} else {
			//Do nothing, clicked in info display
		}
	}
}
