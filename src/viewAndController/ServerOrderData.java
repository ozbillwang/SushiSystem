package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * 
 * @author Yi Zheng
 *
 * A data model for the order table of server application.
 */
public class ServerOrderData{
	private StringProperty ID;
	private StringProperty status;
	private StringProperty content;
	private StringProperty username;
	
	public ServerOrderData(String id, String status, String content, String username) {
		this.ID =  new SimpleStringProperty(id);
		this.status =  new SimpleStringProperty(status);
		this.content =  new SimpleStringProperty(content);
		this.username = new SimpleStringProperty(username);
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(StringProperty username) {
		this.username = username;
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
