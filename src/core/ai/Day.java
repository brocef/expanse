package core.ai;

import core.Populace;

public class Day {
	private static int date = 0;
	private Populace p;
	
	public Day(Populace pop) {
		this.p = pop;
	}
	
	public Populace getPopulace() {
		return this.p;
	}
	
	public static int getDate() {
		return date;
	}
	
	public static void incrementDate() {
		date++;
	}
}
