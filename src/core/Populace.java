package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

import core.ai.Action;
import core.ai.Day;
import core.ai.Sentinence;

public class Populace {
	public static ArrayList<Populace> master_pop_list = new ArrayList<Populace>();
	private static long id_counter = 10000;
	private long id;
	
	private Tile hearth;
	private int pop;
	private double stockpile[];
	
	private Sentinence ai;
	
	
	private Populace(Tile t, int starting_pop, Sentinence ai) {
		id = id_counter;
		pop = starting_pop;
		stockpile = new double[Resource.Type.values().length];
		hearth = t;
		this.ai = ai;
	}
	
	public Queue<Action> nextDay(Day d) {
		return ai.doStuff(d);
	}
	
	public Tile getHearth() {
		return this.hearth;
	}
	
	public int getPopulation() {
		return this.pop;
	}
	
	public double[] getResourceStockpile() {
		return Arrays.copyOf(stockpile, stockpile.length);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Populace) {
			Populace p = (Populace) o;
			return p.id == this.id;
		}
		return false;
	}
	
	public static Populace createPopulace(Tile t, int starting_pop, Class<?> ai) {
		Sentinence newai = null;
		try {
			newai = (Sentinence)(ai.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.err.println("Failed to make new ai instance");
			return null;
		}
		Populace p = new Populace(t, starting_pop, newai);
		if (t.settle(p)) {
			id_counter++;
			master_pop_list.add(p);
		} else
			return null;
		return p;
	}
}
