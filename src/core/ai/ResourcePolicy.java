package core.ai;

import core.Resource;

public class ResourcePolicy {
	private int labor;
	private double efficiency;
	private Resource.Type resource;
	
	public ResourcePolicy(int workforce, Resource.Type resource) {
		this.labor = workforce;
		this.resource = resource;
		efficiency = 0;
	}
	
	public double getExtractionEfficiency() {
		return efficiency;
	}
	
	public void setWorkforce(int newWorkforce) {
		labor = newWorkforce;
	}
	
	public Resource.Type getResource() {
		return resource;
	}
	
	public double getWorkforceSize() {
		return labor;
	}
}
