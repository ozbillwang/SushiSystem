package viewAndController;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Tab;

import javafx.scene.control.Spinner;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
/**
 * 
 * @author Yi Zheng
 *
 * A controller class for Client application.
 */

public class ClientGUIController {
	
	// FXML fields 
	@FXML
	private TableView<ClientSushiDishesData> menu;
	@FXML
	private TableView<ClientCartData> cart;
	@FXML
	private TableView<ClientOrderData> order;
	
	@FXML
	private Tab menuTab;
	@FXML
	private TableColumn<ClientSushiDishesData,String> menuName;
	@FXML
	private TableColumn<ClientSushiDishesData,String> menuDescr;
	@FXML
	private TableColumn<ClientSushiDishesData,String> menuPrice;
	@FXML
	private TableColumn<ClientSushiDishesData,String> menuLeft;
	@FXML
	private Button addItemB;
	@FXML
	private Spinner<Integer> menuAddSpinner;
	@FXML
	private Button eixtB;
	@FXML
	private Tab cartTab;
	@FXML
	private TableColumn<ClientCartData,String> cartName;
	@FXML
	private TableColumn<ClientCartData,String> cartPrice;
	@FXML
	private TableColumn<ClientCartData,Integer> cartAmount;
	@FXML
	private Button removeItemB;
	@FXML
	private Button confirmB;
	@FXML
	private Button refreshB;
	@FXML
	private Tab orderTab;
	@FXML
	private TableColumn<ClientOrderData,String> orderID;
	@FXML
	private TableColumn<ClientOrderData,String> orderStatus;
	@FXML
	private TableColumn<ClientOrderData,String> orderContent;
	@FXML
	private Button orderRefresh;

	// Data fields of the class.
	private ObservableList<ClientSushiDishesData> sushiData;	
	private ObservableList<ClientCartData> cartData = FXCollections.observableArrayList();  // This list is held locally by the GUI.
	private ObservableList<ClientOrderData> orderData;
	private Client client;
	
	private ClientSushiDishesData currentSelected = null;

	
	public void setUpTables() {
		this.sushiData = client.getSushiData();
		menuName.setCellValueFactory(new PropertyValueFactory<ClientSushiDishesData, String>("name"));
		menuDescr.setCellValueFactory(new PropertyValueFactory<ClientSushiDishesData, String>("description"));
		menuPrice.setCellValueFactory(new PropertyValueFactory<ClientSushiDishesData, String>("price"));
		menuLeft.setCellValueFactory(new PropertyValueFactory<ClientSushiDishesData, String>("left"));
		menu.setItems(sushiData);
		menu.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->changeCurrentSelectedDish(newValue));
		
		cartName.setCellValueFactory(new PropertyValueFactory<ClientCartData, String>("name"));
		cartPrice.setCellValueFactory(new PropertyValueFactory<ClientCartData, String>("price"));
		cartAmount.setCellValueFactory(new PropertyValueFactory<ClientCartData, Integer>("amount"));
		
		
		this.orderData = client.getOrderData();
		orderID.setCellValueFactory(new PropertyValueFactory<ClientOrderData, String>("ID"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<ClientOrderData, String>("status"));
		orderContent.setCellValueFactory(new PropertyValueFactory<ClientOrderData, String>("content"));
		
		
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void changeCurrentSelectedDish(ClientSushiDishesData sd) {
		this.currentSelected = sd;
		System.out.println(currentSelected);
	}
	
	
	public void addToCart() {
		if(!(currentSelected==null)) {
			boolean added = false; // to check whether the item exists in the cart.
			ClientCartData addedCartItem = null;
			for(ClientCartData cd: cartData) {
				if(cd.getName().equals(currentSelected.getName())){
					added = true;
					addedCartItem = cd;
				}
			}
			if(!added) {
				cartData.add(new ClientCartData(currentSelected.getName(),currentSelected.getPrice(),(Integer) menuAddSpinner.getValue()));
			}
			else {
				Integer oldAmount = addedCartItem.getAmount();
				String oldName = addedCartItem.getName();
				String oldPrice = addedCartItem.getPrice();
				cartData.remove(cartData.indexOf(addedCartItem));
				cartData.add(new ClientCartData(oldName, oldPrice, oldAmount+(Integer)menuAddSpinner.getValue()));

			}
			
			System.out.println("added");
		}
		else {
			System.out.println("Not added");
		}
		
	}
	
	public void removeFromCart() {
		if(!(cart.getSelectionModel().getSelectedItem()==null)) {
			int indexToRemove = cart.getSelectionModel().getSelectedIndex();
			if(!(indexToRemove>=cartData.size())) {
				cartData.remove(indexToRemove);
				
				// I choose to update the tableview instead of removing the column:
				// Because there will be a IndexOutOfBoundary exception, if the remove function is called serveral times quickly
				// I guess it's because the program need time to process removing and update indexToRemove. Or a multithread problem.
				updateCart();
			}
		}
	}
	
	public void createOrder() {
		System.out.println("Create order");
		String content = "";
		
		for(ClientCartData cd: cartData) {
			content = content +" "+ cd.getName() +"-"+ cd.getAmount();
		}
		
		client.sendRequest("ord:" + client.getUsername() +":"+ (Integer.toString(new Random().nextInt(100000)))+client.getUsername()+":"+content);
		client.checkMessage();
		updateOrder();
	}
	
	public void updateMenu() {
		if(client!=null) {
			sushiData.clear();
			client.sendRequest("showmeMenu");
			client.checkMessage();
			menu.setItems(sushiData);
		}
	}
	
	public void updateCart() {
		System.out.println("cart updated");
		cart.setItems(cartData);
	}
	
	public void updateOrder() {
		System.out.println(client.getUsername());
		client.sendRequest("showmeOrder:"+client.getUsername());
		orderData.clear();
		client.checkMessage(); 
		order.setItems(orderData);
	}
	
	/*
	 * Setters and Getters.
	 */
	
	public ObservableList<ClientSushiDishesData> getSushiData() {
		return sushiData;
	}

	public void setSushiData(ObservableList<ClientSushiDishesData> sushiData) {
		this.sushiData = sushiData;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
