package core.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.Resource;
import core.Tile;

public class InfoDisplay {
	private static final int WIDTH = 200;
	private Tile selected;
	
	public InfoDisplay() {
		selected = null;
	}
	
	public void selectTile(Tile t) {
		this.selected = t;
	}
	
	public boolean isSelected(Tile t) {
		return selected != null && this.selected.equals(t);
	}
	
	public void draw(Graphics2D g) {
		Rectangle bounds = g.getClipBounds();
		int basex = (int) bounds.getWidth()-WIDTH;
		g.setColor(Color.BLACK);
		g.drawString("Info Display", basex+5, 10);
		g.drawString("Selected Tile (x, y): ", basex+5, 25);
		g.drawString("Terrain Modifier: ", basex+5, 40);
		if (selected != null) {
			g.drawString("("+selected.getX()+", "+selected.getY()+")", basex+110, 25);
			g.drawString(Double.toString(selected.getTerrainModifier()), basex+100, 40);
			Resource[] rsc = selected.getResources();
			int rsclen = rsc.length; //For optimization
			g.drawString("Tile Resources ("+rsclen+")", basex+5, 55);
			for (int i=0; i<rsclen; i++) {
				g.drawString(rsc[i].getName()+" amt remaining: "+rsc[i].getRemainingResources(), basex+5, 70+15*i);
			}
			if (selected.getPopulace() != null)
				g.drawString("Population: "+selected.getPopulace().getPopulation(), basex+5, 15*rsclen+70);
		} else {
			g.drawString("(n/a, n/a)", basex+110, 25);
			g.drawString("n/a", basex+100, 40);
			g.drawString("n/a", basex+5, 55);
		}
	}
}
