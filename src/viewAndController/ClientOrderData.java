package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the order table of client application.
 */

public class ClientOrderData {
	private StringProperty ID;
	private StringProperty status;
	private StringProperty content;
	
	public ClientOrderData(String id, String status, String content) {
		this.ID =  new SimpleStringProperty(id);
		this.status =  new SimpleStringProperty(status);
		this.content =  new SimpleStringProperty(content);
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

	public String getContent() {
		return content.get();
	}

	public void setContent(StringProperty content) {
		this.content = content;
	}
	
	
}
