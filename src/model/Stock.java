package model;
/**
 * @author Yi Zheng
 *  Class to hold the current stock level and restocking level of dishes and ingredients. (like a pair of data)
 */

public class Stock {
	

	private int stockLevel;
	private int restockLevel;

	/**
	 * 
	 * @param stockLevel how many left in stock
	 * @param restockLevel a level at which the stockmanagement will find someone to make more 
	 */
	
	public Stock(int stockLevel, int restockLevel) {
		this.stockLevel = stockLevel;
		this.restockLevel = restockLevel;
	}
	
	/*
	 * Setters and Getters.
	 */
	
	public synchronized void incrementStock(int amount) {
		this.stockLevel+= amount;
	}
	
	public synchronized void decrementStock(int amount) {
		this.stockLevel-= amount;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}

	public int getRestockLevel() {
		return restockLevel;
	}

	public void setRestockLevel(int restockLevel) {
		this.restockLevel = restockLevel;
	}
	
}
