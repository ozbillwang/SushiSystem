package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * 
 * @author Yi Zheng
 *
 * A data model for the supplier table of server application.
 */

public class ServerSupplierData {
	private StringProperty name;
	private StringProperty distance;
	
	public ServerSupplierData(String name, String distance) {
		this.name = new SimpleStringProperty(name);
		this.distance = new SimpleStringProperty(distance);
	}

	public String getName() {
		return name.get();
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public String getDistance() {
		return distance.get();
	}

	public void setDistance(StringProperty distance) {
		this.distance = distance;
	}
	
	
}
