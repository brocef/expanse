package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Terrain {
	private ArrayList<Type> types;
	private HashMap<Type, Double> typevals;
	private Color color;
	
	public Terrain(Map<Type, Double> typevals) throws Exception {
		if (typevals == null || typevals.size() == 0)
			throw new Exception("Invalid Map of Terrain Types... Null or size 0");
		this.typevals = new HashMap<Type, Double>(typevals);
		this.types = new ArrayList<Type>(typevals.keySet());
		Double max = new Double(0.0);
		Type maxtype = null;
		for (Type t:this.types) {
			if (typevals.get(t) > max) {
				max = typevals.get(t);
				maxtype = t;
			}
		}
		this.color = maxtype.getColor();
	}
	
	public Color getTerrainColor() {
		return color;
	}
	
	public double getTerrainModifier() {
		double sum = 0;
		int count = 0;
		for (Type t:Type.values()) {
			if (typevals.containsKey(t)) {
				sum += (typevals.get(t)*t.getModifier());
				count++;
			}
		}
		return sum/count;
	}
	
	public static enum Type {
		PLAINS(Color.decode("#ADEB1F"), 0.1),
		FOREST(Color.decode("#244700"), 0.4),
		MOUNTAINS(Color.decode("#70705C"), 0.6),
		HILLS(Color.decode("#999E00"), 0.3);
		
		private Color color;
		private double mod;
		Type(Color c, double mod) {
			this.color = c;
			this.mod = mod;
		}
		
		public double getModifier() {
			return this.mod;
		}
		
		public Color getColor() {
			return this.color;
		}
	}
}
