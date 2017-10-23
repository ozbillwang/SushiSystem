/**
 * @author Yi Zheng
 * 
 * This class starts the client application. There can be multiple client applications running at the same time.
 */

import viewAndController.LoginGUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Client;

public class InitLoginGUI extends Application{
	
	private Client client;	// The socket associated to the GUI.
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root;
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("viewAndController/LoginGUI.fxml"));
			initClientSocket();
			
			root = loader.load();
			
			LoginGUIController loginctrl = loader.getController();
			loginctrl.setClient(this.client);
			Scene login = new Scene(root, 449, 282);
			
			primaryStage.setScene(login);
			primaryStage.setTitle("Welcome to Sushi Restaurant!");
			primaryStage.show();
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void initClientSocket() {
		client = new Client();
	}
}
