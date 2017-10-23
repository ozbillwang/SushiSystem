package viewAndController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * 
 * @author Yi Zheng
 *
 * A data model for the sushi dish table of server application.
 */


public class ServerSushiData {
	private StringProperty name;
	private StringProperty description;
	private IntegerProperty price;
	private StringProperty recipe;
	private IntegerProperty stock;
	private IntegerProperty restock;

	public ServerSushiData(String name, String descr, Integer price, String recipe, Integer stock, Integer restock) {
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(descr);
		this.price = new SimpleIntegerProperty(price);
		this.recipe = new SimpleStringProperty(recipe);
		this.stock = new SimpleIntegerProperty(stock);
		this.restock = new SimpleIntegerProperty(restock);
	}

	public String getName() {
		return name.get();
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(StringProperty description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price.get();
	}

	public void setPrice(IntegerProperty price) {
		this.price = price;
	}

	public String getRecipe() {
		return recipe.get();
	}

	public void setRecipe(StringProperty recipe) {
		this.recipe = recipe;
	}

	public Integer getStock() {
		return stock.get();
	}

	public void setStock(IntegerProperty stock) {
		this.stock = stock;
	}

	public Integer getRestock() {
		return restock.get();
	}

	public void setRestock(IntegerProperty restock) {
		this.restock = restock;
	}
}

