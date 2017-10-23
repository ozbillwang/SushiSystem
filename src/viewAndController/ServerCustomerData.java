package viewAndController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Yi Zheng
 *
 * A data model for the customer table of client application.
 */

public class ServerCustomerData {
	private StringProperty ID;
	private StringProperty name;
	private StringProperty email;
	private StringProperty phone;
	private StringProperty address;
	private StringProperty loginInfo;
	
	public ServerCustomerData(String id, String name,String email, String phone, String address, String loginInfo) {
		this.ID = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty( email);
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(phone);
		this.loginInfo = new SimpleStringProperty(loginInfo);
	}

	public String getID() {
		return ID.get();
	}

	public void setID(StringProperty iD) {
		ID = iD;
	}

	public String getName() {
		return name.get();
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(StringProperty phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(StringProperty address) {
		this.address = address;
	}

	public String getLoginInfo() {
		return loginInfo.get();
	}

	public void setLoginInfo(StringProperty loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	
}
