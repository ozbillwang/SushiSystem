/**
 * @author Yi Zheng
 * 
 * This class starts the server application. There can be only one server running at this version.
 * Multi-server version will be updated when I finished it. 
 * 
 */


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Order;
import model.Server;
import viewAndController.AlertPopup;
import viewAndController.ServerGUIController;


public class InitServerGUI extends Application {
	private Server server;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAndController/ServerGUI.fxml"));
			root = loader.load();
			this.server = new Server();
			Scene serverGUI = new Scene(root);
			
			
			ServerGUIController sgctrl = loader.getController();
			sgctrl.setServer(server);
			
			// The server is running in a different thread, because it cannot work the GUI at the same time. 
			Thread t = new Thread(server);
			t.start();
			sgctrl.setUp();
			
			primaryStage.setHeight(750);
			primaryStage.setWidth(1100);
			primaryStage.setScene(serverGUI);
			primaryStage.setTitle("Sushi Command Center");
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
					
				}
			});

		}
		catch (Exception e) {
			
		}
		
		
		
	}

}
