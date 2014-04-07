package core;

import core.ai.ResourcePolicy;

public class Resource {
	private String name;
	private Type t;
	private double remaining;//, in_use;
	//private double decay_factor, regen_factor;
	private double laborEfficiency;
	//private double resource_cap;
	
	public static Resource createResource(Type type, String name) {
		double start = Math.random()*5000+5000;
		Resource r = new Resource(type, name, start, type.getLaborModifier());
		return r;
	}
	
	private Resource(Type type, String name, double initial_amount, double laborEfficiency) {
		this.name = name;
		this.t = type;
		this.remaining = initial_amount;
		this.laborEfficiency = type.getLaborModifier()+laborEfficiency;
	}
	
	public void regenerate() {
		remaining *= this.t.regenRate;
	}
	
	public double getRemainingResources() {
		return Math.floor(remaining);
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return t;
	}
	
	public double getLaborFactor() {
		return laborEfficiency;
	}
	
	public double calculateAmountExtracted(ResourcePolicy rp) {
		double gathered;
		gathered = rp.getWorkforceSize() * (laborEfficiency + rp.getExtractionEfficiency());
		if (gathered > remaining) {
			gathered = remaining;
		}
		return gathered;
	}
	
	public double extract(int labor) {
		double gathered;
		gathered = labor * laborEfficiency;
		if (gathered > remaining) {
			gathered = remaining;
			remaining = 0;
		} else
			remaining -= gathered;
		
		return gathered;
	}
	
	
	public static enum Type {
		CROP(1.0, 1.1), TIMBER(1.0, 1.04), METAL(1.0, 1.001);
		
		private double laborModifier, regenRate;
		private Type (double laborMod, double regenRate) {
			this.laborModifier = laborMod;
			this.regenRate = regenRate;
		}
		public double getLaborModifier() {
			return laborModifier;
		}
		public double getRegenRate() {
			return regenRate;
		}
	}
}
