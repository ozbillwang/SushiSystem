package model;
/*
 * Class implements different kinds of ingredients for making a sushi dish. 
 * assume each ingredient is sold by exactly one supplier, and one supplier could sell multiple ingredients
 */

public class Ingredient{
	private String name;
	private String unit;
	private Supplier supplier;
	private Stock stock;
	
	public Ingredient(String name, String unit, Supplier supplier, Stock stock){
		this.name = name;
		this.unit = unit;
		this.supplier = supplier;
		this.setStock(stock);
	}

	/*
	 * Setters and Getters
	 */
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getUnit(){
		return unit;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public Supplier getSupplier(){
		return supplier;
	}

	public void setSupplier(Supplier supplier){
		this.supplier = supplier;
	}

	public synchronized Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
