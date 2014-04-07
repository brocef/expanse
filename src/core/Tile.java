package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tile {
	private int x, y; // Center of tile
	private Polygon poly;
	private Terrain terrain;
	private ArrayList<Resource> resources;
	private Populace settlers;
	//private Tile[] neighbors;
	
	public Tile(int x, int y) {
		this.settlers = null;
		this.x = x;
		this.y = y;
		int pxx = x*45;
		int pxy = y*26;
		int[] xpts = {pxx+30, pxx+15, pxx-15, pxx-30, pxx-15, pxx+15};
		int[] ypts = {pxy, pxy-26, pxy-26, pxy, pxy+26, pxy+26};
		this.poly = new Polygon(xpts, ypts, 6);
		int ran = (int)(Math.random()*4);
		Terrain.Type type = Terrain.Type.values()[ran];
		HashMap<Terrain.Type, Double> map = new HashMap<Terrain.Type, Double>();
		map.put(type, new Double(1.0));
		try {
			this.terrain = new Terrain(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.resources = new ArrayList<Resource>();
		int num = Resource.Type.values().length;
		boolean picked[] = new boolean[num];
		Arrays.fill(picked, false);
		ran = (int)(Math.random()*(num+1));
		for (int i=0; i<ran; i++) {
			int ranRsc;
			do {
				ranRsc = (int)(Math.random()*num);
			} while(picked[ranRsc]);
			picked[ranRsc] = true;
			Resource r = Resource.createResource(Resource.Type.values()[ranRsc], Resource.Type.values()[ranRsc].name());
			this.resources.add(r);
		}
	}
	
	
	
	public Populace getPopulace() {
		return this.settlers;
	}
	
	public boolean settle(Populace p) {
		if (this.settlers == null) {
			this.settlers = p;
			return true;
		}
		return false;
	}
	
	public Color getFillColor() {
		return this.terrain.getTerrainColor();
	}
	
	public Resource[] getResources() {
		return resources.toArray(new Resource[resources.size()]);
	}
	
	public double getTerrainModifier() {
		return this.terrain.getTerrainModifier();
	}
	
	public Polygon getPolygon() {
		return this.poly;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void drawTile(Graphics2D g, Polygon p) {
		g.setColor(getFillColor());
		g.fillPolygon(p);
		if (settlers != null) {
			g.setColor(Color.WHITE);
			Rectangle r = p.getBounds();
			int cx = (r.x-15)+r.width/2;
			int cy = (r.y)+r.height/2;
			g.drawString(Integer.toString(settlers.getPopulation()), cx, cy);
		}
		g.setColor(Color.BLACK);
		g.drawPolygon(p);
	}
}
