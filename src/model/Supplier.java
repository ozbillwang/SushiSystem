package model;
/**
 * @author Yi Zheng
 * Class to represent suppliers of sushi ingredients. 
 * 
 */


public class Supplier{
	private String name;
	private int distance; // assume it is in kms and manually entered.
							 // in the real world, the distance may depends on traffic, security issues, etc.
							 // so it would be better to enter it manually. 
	
	/**
	 * 
	 * @param name the name of supplier
	 * @param distance the distance to the business (how far to deliver the ingredient(s))
	 */
	public Supplier(String name, int distance) {
		this.setName(name);
		this.setDistance(distance);
	}

	/*
	 * Setters and Getters
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
