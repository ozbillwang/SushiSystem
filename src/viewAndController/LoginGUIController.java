package viewAndController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;


/**
 * 
 * @author Yi Zheng
 *
 * A controller for Login window (client application).
 */


public class LoginGUIController {
	
	// FXML GUI Components.
	@FXML
	private Button loginB;
	@FXML
	private Button registerB;
	@FXML
	private Button exitB;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;

	// Data fields.
	private Client client;	// The client socket associated to the GUI.	
	
	
	
	public void setClient(Client c) {
		this.client = c;
	}
	
	@FXML
	public void loginEvent(ActionEvent e) {
		String username = userNameField.getText();
		String password = passwordField.getText();
		if(username.equals("") || password.equals("")) {
			AlertPopup alert = new AlertPopup(AlertType.ERROR, "Empty field(s) detected", null,
					"Please fill in all fields.");
		}
		else {
			client.sendRequest("lg"+":" + username +":" + password);
			client.setUsername(username);
			client.checkMessage();
		}
	}
	
	@FXML
	public void register(ActionEvent e) {
		
		Parent layout;
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterGUI.fxml"));
			
			layout = loader.load();
			
			RegisterGUIController regCtrl = loader.getController();
			regCtrl.setClient(client);
			
			Scene s = new Scene(layout, 367,382);
			Stage popup = new Stage();
			popup.sizeToScene();
			popup.initModality(Modality.WINDOW_MODAL);
			popup.setScene(s);
			popup.showAndWait();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	@FXML
	public void exit() {
		client.exit();
		System.exit(0);
	}
}
