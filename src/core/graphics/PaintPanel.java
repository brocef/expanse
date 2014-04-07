package core.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JPanel;

import core.Tile;

public class PaintPanel extends JPanel {
	private static final long serialVersionUID = 4176579446132273921L;
	private Viewport view;
	private int shiftx, shifty;
	
	public PaintPanel(Viewport view) {
		this.view = view;
		shiftx = 0;
		shifty = 0;
	}
	
	public void changeCenter(int newx, int newy) {
		this.shiftx = (this.getWidth()-200)/2-newx;
		this.shifty = (this.getHeight())/2-newy;
	}
	
	@Override
	public void paint(Graphics gfx) {
		super.paint(gfx);
		Graphics2D g = (Graphics2D) gfx;
		
		g.setStroke(new BasicStroke(2));
		Tile[][] tilez = view.getMapTiles();
		Tile selected = null;
		for (int i=0; i<tilez.length; i++) {
			for (int j=0; j<tilez[i].length; j++) {
				if (tilez[i][j] == null) continue;
				Polygon orig = tilez[i][j].getPolygon();
				Polygon p = new Polygon(orig.xpoints, orig.ypoints, orig.npoints);
				p.translate(shiftx, shifty);
				Rectangle mainview = g.getClip().getBounds();
				mainview.setBounds((int) mainview.getX(), (int) mainview.getY(), (int) mainview.getWidth()-200, (int) mainview.getHeight());
				if (!mainview.intersects(p.getBounds2D())) continue;
				tilez[i][j].drawTile(g, p);
				
				if (view.isTileSelected(tilez[i][j]))
					selected = tilez[i][j];
			}
		}
		if (selected != null) {
			g.setColor(Color.CYAN);
			Polygon orig = selected.getPolygon();
			Polygon p = new Polygon(orig.xpoints, orig.ypoints, orig.npoints);
			p.translate(shiftx, shifty);
			g.drawPolygon(p);
		}
		
		g.clearRect((int) g.getClipBounds().getWidth()-200, 0, 200, (int) g.getClipBounds().getHeight()+1);
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.BLACK);
		g.drawLine((int) g.getClipBounds().getWidth()-200, 0, (int) g.getClipBounds().getWidth()-200, (int) g.getClipBounds().getHeight()+1);
		view.getInfoDisplay().draw(g);
	}
}
