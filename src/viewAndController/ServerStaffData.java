package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * 
 * @author Yi Zheng
 *
 * A data model for the staff table of server application.
 */

public class ServerStaffData {
	private StringProperty ID;
	private StringProperty name;
	private StringProperty status;
	private StringProperty description;
	
	public ServerStaffData(String id, String name, String status, String descr) {
		this.ID = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.status = new SimpleStringProperty(status);
		this.description = new SimpleStringProperty(descr);
	}

	public String getID() {
		return ID.get();
	}

	public void setID(StringProperty id) {
		this.ID = id;
	}

	public String getName() {
		return name.get();
	}

	public void setName(StringProperty name) {
		this.name = name;
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
		description = description;
	}
	
	
}
