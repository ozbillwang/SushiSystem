package model;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Client;
import viewAndController.ClientGUIController;
import viewAndController.LoginGUIController;
import viewAndController.ClientSushiDishesData;

/**
 * 
 * @author Yi Zheng
 *
 * The instance of this class will be created by InitLoginGUI.java
 * This class is to open the client window that clients can view dishes, orders and place new orders etc.
 */

public class InitClientGUI extends Application{
	
	private Client client;
	
	/**
	 * Constructor.
	 * @param client The client socket.
	 */
	public InitClientGUI(Client client) {
		this.client = client;
		Stage s = new Stage();
		try {
			start(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root;
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../viewAndController/ClientGUI.fxml"));
			
			root = loader.load();
			
			Scene login = new Scene(root);
			
			ClientGUIController ctrl = loader.getController();
			ctrl.setClient(client);
			
			// Gather information from server.
			
			client.sendRequest("showmeMenu");
			
			client.checkMessage();
			
			client.sendRequest("showmeOrder:"+client.getUsername());
			
			ctrl.setUpTables();
			primaryStage.setScene(login);
			primaryStage.setTitle("Sushi Restaurant! How can I help you?");
			primaryStage.show();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
