package viewAndController;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * @author Yi Zheng
 * 
 * A class to avoid duplicated code in other classes. 
 * When it is created, an alert infromation window will pop out.
 * 
 *
 */

public class AlertPopup {
	public AlertPopup(AlertType a, String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
