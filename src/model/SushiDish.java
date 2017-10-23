package model;

import java.util.Hashtable;

/**
 * @author Yi Zheng
 *  Class to represent a sushi dish.
 *  including information: name, description, price and recipe of this dish.
 */

public class SushiDish{

	private String name;
	private String description;
	private Integer price;		
	private Hashtable<Ingredient, Integer> recipe;
	private Stock stock;
	
	/**
	 * 
	 * @param name the name of sushi dish.
	 * @param description the description of the dish, may includes allergic information etc.
	 * @param price the price of the dish. Default to be £ xx per dish.
	 * @param recipe what ingredients are used to make the dish
	 * @param stock a pair of data that includes the stock level and the restocking level.
	 */
	public SushiDish(String name, String description, Integer price, Hashtable<Ingredient, Integer> recipe, Stock stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.recipe = recipe;
		this.stock = stock;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Hashtable<Ingredient, Integer> getRecipe() {
		return recipe;
	}

	public void setRecipe(Hashtable<Ingredient, Integer> recipe) {
		this.recipe = recipe;
	}



	public synchronized Stock getStock() {
		return stock;
	}



	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
