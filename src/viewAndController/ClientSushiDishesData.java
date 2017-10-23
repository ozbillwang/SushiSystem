package viewAndController;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the sushi dish table of client application.
 * 
 * some inner classes for displaying information in the tables.
 * I didn't use the classes in the model, because there are many fields in the classes that user should not seen.  e.g. SushiDish: stock.    
 */

public class ClientSushiDishesData {
	private StringProperty name;
	private StringProperty description;
	private StringProperty price;
	private StringProperty left;
	
	public ClientSushiDishesData(String n, String descr, String p, String left) {
		this.name = new SimpleStringProperty(n);
		this.description = new SimpleStringProperty(descr);
		this.price = new SimpleStringProperty(p);
		this.left =new SimpleStringProperty(left);
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
	public String getPrice() {
		return price.get();
	}
	public void setPrice(StringProperty price) {
		this.price = price;
	}
	public String getLeft() {
		return left.get();
	}
	public void setLeft(StringProperty left) {
		this.left = left;
	}
	
	
	
}