package viewAndController;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the recipe table of server application.
 */

public class ServerRecipeData {
	private StringProperty ingreName;
	private StringProperty ingreUnit;
	private IntegerProperty amount;
	
	public ServerRecipeData(String name, String unit, Integer used) {
		this.ingreName = new SimpleStringProperty(name);
		this.ingreUnit = new SimpleStringProperty(unit);
		this.amount = new SimpleIntegerProperty(used);
	}

	public Integer getAmount() {
		return amount.get();
	}

	public void setAmount(IntegerProperty amount) {
		this.amount = amount;
	}

	public String getIngreName() {
		return ingreName.get();
	}

	public void setIngreName(StringProperty ingreName) {
		this.ingreName = ingreName;
	}

	public String getIngreUnit() {
		return ingreUnit.get();
	}

	public void setIngreUnit(StringProperty ingreUnit) {
		this.ingreUnit = ingreUnit;
	}
	
	
}
