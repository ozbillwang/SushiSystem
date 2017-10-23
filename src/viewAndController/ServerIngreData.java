package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the ingredient table of server application.
 */

public class ServerIngreData {
	private StringProperty name;
	private StringProperty supplier;
	private StringProperty unit;
	private StringProperty stockLevel;
	private StringProperty restockLevel;
	
	public ServerIngreData(String name, String supplier, String unit, String stock, String restock) {
		this.name = new SimpleStringProperty(name);
		this.supplier = new SimpleStringProperty(supplier);
		this.unit = new SimpleStringProperty(unit);
		this.stockLevel = new SimpleStringProperty(stock);
		this.restockLevel  = new SimpleStringProperty(restock);
		
	}

	public String getStockLevel() {
		return stockLevel.get();
	}

	public void setStockLevel(StringProperty stockLevel) {
		this.stockLevel = stockLevel;
	}

	public String getRestockLevel() {
		return restockLevel.get();
	}

	public void setRestockLevel(StringProperty restockLevel) {
		this.restockLevel = restockLevel;
	}

	public String getName() {
		return name.get();
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier.get();
	}

	public void setSupplier(StringProperty supplier) {
		this.supplier = supplier;
	}

	public String getUnit() {
		return unit.get();
	}

	public void setUnit(StringProperty unit) {
		this.unit = unit;
	}
}
