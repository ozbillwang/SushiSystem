package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the drone table of server application.
 */

public class ServerDroneData {
	private StringProperty ID;
	private StringProperty status;
	private StringProperty description;
	private StringProperty deliverOrBuy;
	
	public ServerDroneData(String id, String status, String description, String deliverOrBuy) {
		this.ID = new SimpleStringProperty(id);
		this.status = new SimpleStringProperty(status);
		this.description = new SimpleStringProperty(description);
		this.deliverOrBuy = new SimpleStringProperty(deliverOrBuy);
	}

	public String getID() {
		return ID.get();
	}

	public void setID(StringProperty iD) {
		ID = iD;
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(StringProperty status) {
		this.status = status;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(StringProperty description) {
		this.description = description;
	}

	public String getDeliverOrBuy() {
		return deliverOrBuy.get();
	}

	public void setDeliverOrBuy(StringProperty deliverOrBuy) {
		this.deliverOrBuy = deliverOrBuy;
	}
	
	
}
