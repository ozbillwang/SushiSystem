package viewAndController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Client;
import javafx.scene.control.TextArea;

import javafx.scene.control.PasswordField;

/**
 * 
 * @author Yi Zheng
 *
 * A Controller for the registration window.
 */

public class RegisterGUIController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField userNameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextArea addressField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField confirmPassField;
	@FXML
	private Button registerB;

	
	private Client client;
	
	@FXML
	public void  reg() {
		String name = nameField.getText();
		String username = userNameField.getText();
		String email =  emailField.getText();
		String phone = phoneField.getText();
		String address = addressField.getText();
		String password = passwordField.getText();
		String confirm = confirmPassField.getText();
		
		if(name.equals("")||username.equals("")||email.equals("")||phone.equals("")||address.equals("")||password.equals("")||confirm.equals("")) {
			AlertPopup alert = new AlertPopup(AlertType.ERROR, "Empty Field(s) Detected", null, 
					"Plesae fill all fields of the form to register.");
		}
		else {
			if(password.equals(confirm)) {
				
				// Read new user details:
				String request = "rg" +":"+ name +":"+ username
									+ ":" + email + ":" + phone+ ":"
									+ address +":"+ password;
				
				client.sendRequest(request);
				
				// Check result.
				
				client.checkMessage();
				
				
			}
			else {
				AlertPopup alert = new AlertPopup(AlertType.ERROR, "Passwords do not match", "Theses passwords don't match:", 
						"Plesae enter the same password in the confirm password field.");
			}

		}
		
	}
	
	public void setClient(Client c) {
		this.client = c;
	}
}
