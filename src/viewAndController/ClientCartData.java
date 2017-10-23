package viewAndController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the cart table of client application.
 */

public class ClientCartData {
	private StringProperty name;
	private StringProperty price;
	private IntegerProperty amount;
	
	
	public ClientCartData(String n, String p, Integer a) {
		this.name = new SimpleStringProperty(n);
		this.price = new SimpleStringProperty(p);
		this.amount  = new SimpleIntegerProperty(a);
	}


	public String getName() {
		return name.get();
	}


	public void setName(StringProperty name) {
		this.name = name;
	}


	public String getPrice() {
		return price.get();
	}


	public void setPrice(StringProperty price) {
		this.price = price;
	}


	public Integer getAmount() {
		return amount.get();
	}


	public void setAmount(IntegerProperty amount) {
		this.amount = amount;
	}
	
	
	
}
